package com.org.spring.boot.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Slf4j
@Service
public class BridgeSender {

    private static final String POINTS = "{ \"messageId\": \"6ab1048deed15a3e21ec2e3b0e7ba6b9\", \"source\": \"tenant\", \"panelId\": \"0000082115\", \"serial\": \"0000082115\", \"event\": \"points\", \"points\": [ { \"address\": \"145-231-0\", \"deviceName\": \"A487 \", \"deviceDescription\": \"ANALOG PSEUDO: A 487 - PSEUDO POINT\", \"extraInfo\": { \"DT\": \"AP256\", \"PT\": \"APSEUDO\", \"CVAL\": \"0\", \"U\": \"0-\" } }, { \"address\": \"175-1-0\", \"deviceName\": \"SIG900 \", \"deviceDescription\": \"TRUEALERT ZONE 1 - ALL APPLIANCE OUTPUTS - SIGNAL CIRCUIT\", \"extraInfo\": { \"DT\": \"VNAC\", \"PT\": \"SIGNAL\", \"C\": \"0-\", \"T\": \"0-\", \"D\": \"0-\" } } ] }";
    private static final String POINTS_FINISHED = "{ \"messageId\": \"6ab1048deed15a3e21ec2e3b0e7ba6b9\", \"source\": \"bridge\", \"panelId\": \"0000082115\", \"serial\": \"0000082115\", \"event\": \"points.finished\" }";
    private static final String PANEL_INFO = "{ \"messageId\": \"6ab1048deed15a3e21ec2e3b0e7ba6b9\", \"source\": \"bridge\", \"panelId\": \"0000082115\", \"serial\": \"0000082115\", \"event\": \"panelinfo\", \"info\": { \"login\": \"1\", \"ctime\": \"170008-7140117\" } }";
    private static final String ACTIVE_DEVICES = "{ \"messageId\": \"6ab1048deed15a3e21ec2e3b0e7ba6b9\", \"source\": \"bridge\", \"panelId\": \"0000082115\", \"serial\": \"0000082115\", \"event\": \"activedevices\", \"info\": { \"total\": 0 } }";
    private static final String ALARM_ON = "{ \"messageId\": \"6ab1048deed15a3e21ec2e3b0e7ba6b9\", \"source\": \"bridge\", \"panelId\": \"0000082115\", \"serial\": \"0000082115\", \"event\": \"alarm.on\", \"pointAddress\": \"3-3-0\" }";
    private static final String ALARM_OFF = "{ \"messageId\": \"6ab1048deed15a3e21ec2e3b0e7ba6b9\", \"source\": \"bridge\", \"panelId\": \"0000082115\", \"serial\": \"0000082115\", \"event\": \"alarm.off\", \"pointAddress\": \"3-3-0\" }";
    private static final String TROUBLE_ON = "{ \"messageId\": \"6ab1048deed15a3e21ec2e3b0e7ba6b9\", \"source\": \"bridge\", \"panelId\": \"0000082115\", \"serial\": \"0000082115\", \"event\": \"trouble.on\", \"pointAddress\": \"3-3-0\" }";
    private static final String TROUBLE_OFF = "{ \"messageId\": \"6ab1048deed15a3e21ec2e3b0e7ba6b9\", \"source\": \"bridge\", \"panelId\": \"0000082115\", \"serial\": \"0000082115\", \"event\": \"trouble.off\", \"pointAddress\": \"3-3-0\" }";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${bridge.messaging.exchange}")
    private String bridgeExchange;

    @Value("${tenant.messaging.exchange}")
    private String tenantExchange;

    @Scheduled(fixedDelay = 10000L)
    public void sendMessage() {
        final MessageProperties messageProperties = new MessageProperties();
        messageProperties.setTimestamp(new Date());
        messageProperties.setPriority(new Random().nextInt(50));
        final Message message = new Message(POINTS_FINISHED.getBytes(), messageProperties);
        log.info("Sending message to Tenant Exchange : {}", new String(message.getBody()));
        rabbitTemplate.convertAndSend(tenantExchange, "jci.tofs.tenant.event.panel.0000082115.points", new Message(POINTS.getBytes(), messageProperties));
        rabbitTemplate.convertAndSend(bridgeExchange, "jci.tofs.bridge.event.panel.0000082115.points", new Message(POINTS_FINISHED
                                                                                                                           .getBytes(), messageProperties));
        rabbitTemplate.convertAndSend(bridgeExchange, "jci.tofs.bridge.event.panel.0000082115.points", new Message(PANEL_INFO
                                                                                                                           .getBytes(), messageProperties));
        rabbitTemplate.convertAndSend(bridgeExchange, "jci.tofs.bridge.event.panel.0000082115.points", new Message(ACTIVE_DEVICES
                                                                                                                           .getBytes(), messageProperties));
        rabbitTemplate.convertAndSend(bridgeExchange, "jci.tofs.bridge.event.panel.0000082115.points", new Message(ALARM_ON.getBytes(), messageProperties));
        rabbitTemplate.convertAndSend(bridgeExchange, "jci.tofs.bridge.event.panel.0000082115.points", new Message(ALARM_OFF
                                                                                                                           .getBytes(), messageProperties));
        rabbitTemplate.convertAndSend(bridgeExchange, "jci.tofs.bridge.event.panel.0000082115.points", new Message(TROUBLE_ON
                                                                                                                           .getBytes(), messageProperties));
        rabbitTemplate.convertAndSend(bridgeExchange, "jci.tofs.bridge.event.panel.0000082115.points", new Message(TROUBLE_OFF
                                                                                                                           .getBytes(), messageProperties));
    }


}
