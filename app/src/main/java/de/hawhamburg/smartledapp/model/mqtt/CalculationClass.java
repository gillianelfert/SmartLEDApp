package de.hawhamburg.smartledapp.model.mqtt;

import java.nio.charset.StandardCharsets;

import de.hawhamburg.smartledapp.model.profile.Profile;

public class CalculationClass {
    private MqttClient mqttClient;
    public Profile currentProfile;
    private static final String BRIGHTNESS = "brightness";
    private static final String DEZIBEL = "values";
    private static final String CLAPMODE = "clapmode";
    private static final String HEXAVALUE = "hexavalue";
    public void mqttConnect() throws InterruptedException{
        mqttClient = new MqttClient();
        mqttClient.connectToBroker("my-mqtt-client-id", "broker.hivemq.com", 1883, "my-user", "my-password");
        Thread.sleep(100);
        mqttClient.subscribe(BRIGHTNESS, (message) -> {
            try {
                var convertedMessageContent = new String(message.getPayloadAsBytes(), StandardCharsets.UTF_8);
                System.out.printf("Message received from topic '%s': '%s'%n", message.getTopic(),
                        convertedMessageContent);
            } catch (Exception e) {

            }
        });

        Thread.sleep(100);
        mqttClient.publish(BRIGHTNESS,"Hallo Welt");
    }

}
