package de.hawhamburg.smartledapp.model.mqtt;

import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;
import com.hivemq.client.mqtt.mqtt3.message.publish.Mqtt3Publish;
import com.hivemq.client.mqtt.mqtt3.message.unsubscribe.Mqtt3Unsubscribe;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class MqttClient {

    private Mqtt3AsyncClient client;

    public void connectToBroker(String identifier, String host, int port, String username, String password) {
        client = com.hivemq.client.mqtt.MqttClient.builder().useMqttVersion3().identifier(identifier).serverHost(host)
                .serverPort(port).buildAsync();

        client.connectWith().simpleAuth().username(username).password(password.getBytes()).applySimpleAuth().send()
                .whenComplete((connAck, throwable) -> {
                    if (throwable != null) {
                        throwable.printStackTrace();
                    } else {
                        Log.info("Connected to '%s:%d'".format(host, port));
                    }
                });
    }


    public void subscribe(String topic, Consumer<Mqtt3Publish> consumer) {
        client.subscribeWith().topicFilter(topic)
                //.callback(publish -> receiveData(publish.getTopic().toString(), new String(publish.getPayloadAsBytes())))
                .callback(consumer).send().whenComplete((subAck, throwable) -> {
                    if (throwable != null) {
                        throwable.printStackTrace();
                    } else {
                        Log.info("Subscribed to '%s'".format(topic));
                    }
                });

    }

    public void publish(String topic, String message) {
        client.publishWith().topic(topic).payload(message.getBytes()).send().whenComplete((publish, throwable) -> {
            if (throwable != null) {
                throwable.printStackTrace();
            }
            Log.info("Message sent to '%s'".format(topic)+" : "+ message);
        });
    }

}
