package de.hawhamburg.smartledapp.model.mqtt;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

@RunWith(JUnit4.class)
public class MqttClientTest extends TestCase {
    private MqttClient mqttClient;


    @Before
    public void setUp(){
        mqttClient = new MqttClient();
    }

    @Test
    public void testConnectToBroker() {
        mqttClient.connectToBroker("my-mqtt-client-id", "broker.hivemq.com", 1883, "my-user", "my-password");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(mqttClient.getClient().getState().isConnected());
    }

    public void testSubscribe() {
    }

    public void testPublish() {
    }
}