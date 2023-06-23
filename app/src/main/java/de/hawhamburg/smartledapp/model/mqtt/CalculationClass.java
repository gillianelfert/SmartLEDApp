package de.hawhamburg.smartledapp.model.mqtt;

import android.content.Context;
import android.content.Intent;

import java.nio.charset.StandardCharsets;

import de.hawhamburg.smartledapp.MyApplication;
import de.hawhamburg.smartledapp.model.profile.Profile;

public class CalculationClass {
    private MqttClient mqttClient;
    private static final String BRIGHTNESS = "brightness";
    private static final String DEZIBEL = "dezibel";
    private static final String MODE = "mode";
    private static final String VALUE = "value";

    private final Integer SOUNDBARRIER = 100;

    private final Integer LIGHTBARRIER = 100;

    private MyApplication myApplication;
    private Context context;

    public CalculationClass(Context context) {
        this.context = context;
    }

    public void setUpUnit() {
        myApplication = (MyApplication) context.getApplicationContext();

        try {
            connectToMQTT();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        listenToTopic(DEZIBEL);
        listenToTopic(BRIGHTNESS);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        mqttClient.publish(DEZIBEL,"Hey");


    }

    public void connectToMQTT() throws InterruptedException{
        mqttClient = new MqttClient();
        mqttClient.connectToBroker("my-mqtt-client-id", "broker.hivemq.com", 1883, "my-user", "my-password");
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
        if (myApplication.getActiveProfile().isReactsToClap()&& Integer.valueOf(message) <= SOUNDBARRIER){
            mqttClient.publish(VALUE,String.valueOf(myApplication.getActiveProfile().getLightBrightness()));
        }else if(!myApplication.getActiveProfile().isReactsToClap()&& Integer.valueOf(message) <= LIGHTBARRIER){
            mqttClient.publish(VALUE, String.valueOf(myApplication.getActiveProfile().getLightBrightness()));
        }
    }

    public void updateMode(){
        if (myApplication.getActiveProfile().isReactsToClap()){
            mqttClient.publish(MODE, "a");
        }else {
            mqttClient.publish(MODE, "l");
        }
    }

    public void disconnectFromBroker(){
        mqttClient.disconnect();
        System.out.println("Disconnected");
    }

}
