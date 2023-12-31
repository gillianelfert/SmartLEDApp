package de.hawhamburg.smartledapp.model.mqtt;

import android.content.Context;

import java.nio.charset.StandardCharsets;

import de.hawhamburg.smartledapp.MyApplication;
import de.hawhamburg.smartledapp.model.profile.Profile;

public class CalculationClass implements Runnable{
    private MqttClient mqttClient;
    private static final String BRIGHTNESS = "brightness";
    private static final String DEZIBEL = "dezibel";
    private static final String MODE = "mode";
    private static final String VALUE = "value";

    public static final int ACTIVATE_AT_DEZIBEL = 100;
    public static final int ACTIVATE_AT_LUMINOSITY = 110;
    private MyApplication myApplication;
    private Context context;

    public CalculationClass(Context context) {
        this.context = context;
    }

    public void setUpUnit(){
        myApplication = (MyApplication) context.getApplicationContext();

        try {
            Thread.sleep(1000);
            connectToMQTT();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        listenToTopic(DEZIBEL);
        listenToTopic(BRIGHTNESS);
    }

    public void connectToMQTT() throws InterruptedException{
        mqttClient = new MqttClient();
        mqttClient.connectToBroker("my-mqtt-client-id", "172.20.10.3", 1883, "Jan", "123");
    }

    public void listenToTopic(String topic){
        mqttClient.subscribe(topic, (message) -> {
            try {
                var convertedMessageContent = new String(message.getPayloadAsBytes(), StandardCharsets.UTF_8);
                System.out.printf("Message received from topic '%s': '%s'%n", message.getTopic(),
                        convertedMessageContent);
                publishingUnit(convertedMessageContent,topic);
            } catch (Exception e) {

            }
        });
    }
    public void publishingUnit(String message, String topic){
        Profile activeProfile = myApplication.getProfileRepository().getActiveProfile();
        if (topic.equals(DEZIBEL)&&activeProfile.isReactsToClap()){
            System.out.println();
            toggleLight(message, activeProfile);
            mqttClient.publish(VALUE, String.valueOf(activeProfile.getLightBrightness()));
        }else if(topic.equals(BRIGHTNESS)&& !activeProfile.isReactsToClap()){
            if(Integer.valueOf(message) > ACTIVATE_AT_LUMINOSITY){
                mqttClient.publish(VALUE, String.valueOf(activeProfile.getLightBrightness()));
            }else{
                mqttClient.publish(VALUE, String.valueOf(0));
            }
        }
        updateMode();
    }

    public void toggleLight(String message, Profile activeProfile) {
        if(Integer.valueOf(message) > ACTIVATE_AT_DEZIBEL){
            if(activeProfile.isLightIsOn()){
                myApplication.getProfileRepository().getActiveProfile().setLightBrightness(0);
            }else if (!activeProfile.isLightIsOn()){
                myApplication.getProfileRepository().getActiveProfile().setLightBrightness(activeProfile.getPreviousLightBrightness());
            }
            myApplication.getProfileRepository().getActiveProfile().toggleLight();
        }
    }

    public void updateMode(){
        if (myApplication.getProfileRepository().getActiveProfile().isReactsToClap()){
            mqttClient.publish(MODE, "a");
        }else {
            mqttClient.publish(MODE, "l");
        }
    }

    @Override
    public void run() {
        setUpUnit();
    }
}
