package de.hawhamburg.smartledapp;

import org.junit.Assert;
import org.junit.Test;
import de.hawhamburg.smartledapp.model.mqtt.MqttClient;


public class MQTTTest {
    @Test
    public void testConnectToBroker() {
        MqttClient mqttClient = new MqttClient();
        String identifier = "my-mqtt-client-id";
        String host = "broker.hivemq.com";
        int port = 1883;
        String username = "my-user";
        String password = "my-password";

        boolean isConnected = mqttClient.connectToBroker(identifier, host, port, username, password);

        Assert.assertTrue(isConnected);
    }
}