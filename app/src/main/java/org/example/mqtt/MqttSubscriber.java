package org.example.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.example.entity.Message;
import org.example.mapper.MessageMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.time.LocalDateTime;

@Component
public class MqttSubscriber implements CommandLineRunner {
    private final MessageMapper messageMapper;

    // 构造注入 Mapper，Spring 会自动给
    public MqttSubscriber(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        String broker = "tcp://localhost:1883";
        String clientId = "java-sub-" + System.currentTimeMillis();

        MqttClient client = new MqttClient(broker, clientId,new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        client.connect(options);
        client.subscribe("test/topic", (topic, message) -> {
            String payload = new String(message.getPayload());
            System.out.println(">>>收到MQTT消息[" + topic + "]:" + payload);
            // 落库：topic + 内容 + 当前时间
            messageMapper.insert(new Message(topic, payload, LocalDateTime.now()));
            System.out.println(">>>已存入MySQL");
        });
        System.out.println(">>>MQTT已连接" + broker + ",监听test/topic");
    }
}