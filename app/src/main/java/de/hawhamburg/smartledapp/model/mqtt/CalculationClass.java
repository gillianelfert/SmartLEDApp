package de.hawhamburg.smartledapp.model.mqtt;

import android.content.Context;

import java.nio.charset.StandardCharsets;

import de.hawhamburg.smartledapp.MyApplication;
import de.hawhamburg.smartledapp.model.profile.Profile;

public class CalculationClass {
    private MqttClient mqttClient;
    private static final String BRIGHTNESS = "brightness";
    private static final String DEZIBEL = "dezibel";
    private static final String MODE = "mode";
    private static final String VALUE = "value";

    private MyApplication myApplication;
    private Context context;

    public CalculationClass(Context context) {
        this.context = context;
    }

    public void setUpUnit(){
        myApplication = (MyApplication) context.getApplicationContext();

        try {
            connectToMQTT();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        listenToTopic(DEZIBEL);
        listenToTopic(BRIGHTNESS);
        mqttClient.publish(DEZIBEL,"200");
    }

    public void connectToMQTT() throws InterruptedException{
        mqttClient = new MqttClient();
        mqttClient.connectToBroker("my-mqtt-client-id", "192.168.178.24", 1883,"Jan","123");
    }

    public void listenToTopic(String topic){
        mqttClient.subscribe(topic, (message) -> {
            try {
                var convertedMessageContent = new String(message.getPayloadAsBytes(), StandardCharsets.UTF_8);
                System.out.printf("Message received from topic '%s': '%s'%n", message.getTopic(),
                        convertedMessageContent);
                calculationUnit(convertedMessageContent);
                calculationUnit(convertedMessageContent);
            } catch (Exception e) {

            }
        });
    }

    public void calculationUnit(String message){
        if (myApplication.getProfileRepository().getActiveProfile().isReactsToClap()){
            mqttClient.publish(VALUE,String.valueOf(myApplication.getProfileRepository().getActiveProfile().getLightBrightness()));
        }else {
            mqttClient.publish(VALUE, String.valueOf(myApplication.getProfileRepository().getActiveProfile().getLightBrightness()));
        }
    }

    public void updateMode(){
        if (myApplication.getProfileRepository().getActiveProfile().isReactsToClap()){
            mqttClient.publish(MODE, "a");
        }else {
            mqttClient.publish(MODE, "l");
        }
    }

}
