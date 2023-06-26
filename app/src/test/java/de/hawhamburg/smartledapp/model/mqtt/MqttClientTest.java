package de.hawhamburg.smartledapp.model.mqtt;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.nio.charset.StandardCharsets;

@RunWith(JUnit4.class)
public class MqttClientTest extends TestCase {
    private MqttClient mqttClient;


    @Test
    public void testConnectToBroker() {
        mqttClient = new MqttClient();
        mqttClient.connectToBroker("my-mqtt-client-id", "broker.hivemq.com", 1883, "my-user", "my-password");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(mqttClient.getClient().getState().isConnected());
    }
    @Test
    public void testSubscribe() {
        mqttClient = new MqttClient();
        mqttClient.connectToBroker("my-mqtt-client-id", "broker.hivemq.com", 1883, "my-user", "my-password");

        var x = mqttClient.subscribe("topic", (message) -> {
            try {
                var convertedMessageContent = new String(message.getPayloadAsBytes(), StandardCharsets.UTF_8);
                System.out.printf("Message received from topic '%s': '%s'%n", message.getTopic(),
                        convertedMessageContent);

            } catch (Exception e) {

            }
        });
        assertTrue(x);
    }
    @Test
    public void testPublish() {
        mqttClient = new MqttClient();
        mqttClient.connectToBroker("my-mqtt-client-id", "broker.hivemq.com", 1883, "my-user", "my-password");

        assertTrue(mqttClient.publish("test","test"));
    }
}