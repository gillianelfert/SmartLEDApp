package de.hawhamburg.smartledapp.model.mqtt;

import android.content.Context;

import java.nio.charset.StandardCharsets;

import de.hawhamburg.smartledapp.MyApplication;
import de.hawhamburg.smartledapp.model.profile.Profile;

public class CalculationClass {
    private MqttClient mqttClient;
    private static final String BRIGHTNESS = "brightness";
    private static final String DEZIBEL = "values";
    private static final String MODE = "mode";
    private static final String VALUE = "value";

    private MyApplication myApplication;
    private Context context;
    private Profile activeProfile;


    public CalculationClass(Context context) {
        this.context = context;
    }

    public void SetUpUnit(){
        myApplication = (MyApplication) context.getApplicationContext();
        activeProfile = myApplication.getActiveProfile();

        try {
            connectToMQTT();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        listenToTopic(DEZIBEL);
        listenToTopic(BRIGHTNESS);
    }

    public void connectToMQTT() throws InterruptedException{
        mqttClient = new MqttClient();
        mqttClient.connectToBroker("my-mqtt-client-id", "broker.hivemq.com", 1883, "my-user", "my-password");
        Thread.sleep(100);


        Thread.sleep(100);
    }

    public void listenToTopic(String topic){
        mqttClient.subscribe(topic, (message) -> {
            try {
                var convertedMessageContent = new String(message.getPayloadAsBytes(), StandardCharsets.UTF_8);
                System.out.printf("Message received from topic '%s': '%s'%n", message.getTopic(),
                        convertedMessageContent);
                calculationUnit(convertedMessageContent);
            } catch (Exception e) {

            }
        });
    }

    public void calculationUnit(String message){
        if (activeProfile.isReactsToClap()){
            mqttClient.publish(VALUE,String.valueOf(activeProfile.getLightBrightness()));
        }else {
            mqttClient.publish(VALUE, String.valueOf(activeProfile.getLightBrightness()));
        }
    }

    public void updateMode(){
        if (activeProfile.isReactsToClap()){
            mqttClient.publish(MODE, "a");
        }else {
            mqttClient.publish(MODE, "l");

        }
    }

}
