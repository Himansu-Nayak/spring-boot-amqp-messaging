package com.org.spring.boot.amqp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessagingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessagingTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${bridge.messaging.exchange}")
    private String bridgeExchange;

    @Value("${tenant.messaging.exchange}")
    private String tenantExchange;

    private MessageProperties messageProperties;

    @Before
    public void setup(){
        messageProperties = new MessageProperties();
        messageProperties.setTimestamp(new Date());
        messageProperties.setPriority(new Random().nextInt(50));
    }

    @Test
    public void verifyPointsEvent(){
        final String points = "{ \"messageId\": \"6ab1048deed15a3e21ec2e3b0e7ba6b9\", \"source\": \"tenant\", \"panelId\": \"0000082115\", \"serial\": \"0000082115\", \"event\": \"points\", \"points\": [ { \"address\": \"145-231-0\", \"deviceName\": \"A487 \", \"deviceDescription\": \"ANALOG PSEUDO: A 487 - PSEUDO POINT\", \"extraInfo\": { \"DT\": \"AP256\", \"PT\": \"APSEUDO\", \"CVAL\": \"0\", \"U\": \"0-\" } }, { \"address\": \"175-1-0\", \"deviceName\": \"SIG900 \", \"deviceDescription\": \"TRUEALERT ZONE 1 - ALL APPLIANCE OUTPUTS - SIGNAL CIRCUIT\", \"extraInfo\": { \"DT\": \"VNAC\", \"PT\": \"SIGNAL\", \"C\": \"0-\", \"T\": \"0-\", \"D\": \"0-\" } } ] }";
        sendEvent(points, tenantExchange, "jci.tofs.tenant.event.panel.0000082115.points");
    }

    @Test
    public void verifyPanelEvent(){
        final String panel = "{ \"messageId\": \"6ab1048deed15a3e21ec2e3b0e7ba6b9\", \"source\": \"bridge\", \"panelId\": \"0000082115\", \"serial\": \"0000082115\", \"event\": \"panelinfo\", \"info\": { \"login\": \"1\", \"ctime\": \"170008-7140117\" } }";
        sendEvent(panel, bridgeExchange, "jci.tofs.bridge.event.panel.0000082115.points");
    }

    @Test
    public void verifyPointsFinishedEvent(){
        final String pointsFinished = "{ \"messageId\": \"6ab1048deed15a3e21ec2e3b0e7ba6b9\", \"source\": \"bridge\", \"panelId\": \"0000082115\", \"serial\": \"0000082115\", \"event\": \"points.finished\" }";
        sendEvent(pointsFinished, bridgeExchange, "jci.tofs.bridge.event.panel.0000082115.points");
    }

    @Test
    public void verifyActiveDevice(){
        final String activeDevice = "{ \"messageId\": \"6ab1048deed15a3e21ec2e3b0e7ba6b9\", \"source\": \"bridge\", \"panelId\": \"0000082115\", \"serial\": \"0000082115\", \"event\": \"activedevices\", \"info\": { \"total\": 0 } }";
        sendEvent(activeDevice, bridgeExchange, "jci.tofs.bridge.event.panel.0000082115.points");
    }

    @Test
    public void verifyAlarmOn(){
        final String alarmOn = "{ \"messageId\": \"6ab1048deed15a3e21ec2e3b0e7ba6b9\", \"source\": \"bridge\", \"panelId\": \"0000082115\", \"serial\": \"0000082115\", \"event\": \"alarm.on\", \"pointAddress\": \"3-3-0\" }";
        sendEvent(alarmOn, bridgeExchange, "jci.tofs.bridge.event.panel.0000082115.points");
    }

    @Test
    public void verifyAlarmOff(){
        final String alarmOf = "{ \"messageId\": \"6ab1048deed15a3e21ec2e3b0e7ba6b9\", \"source\": \"bridge\", \"panelId\": \"0000082115\", \"serial\": \"0000082115\", \"event\": \"alarm.off\", \"pointAddress\": \"3-3-0\" }";
        sendEvent(alarmOf, bridgeExchange, "jci.tofs.bridge.event.panel.0000082115.points");
    }

    @Test
    public void verifyTroubleOn(){
        final String troubleOn = "{ \"messageId\": \"6ab1048deed15a3e21ec2e3b0e7ba6b9\", \"source\": \"bridge\", \"panelId\": \"0000082115\", \"serial\": \"0000082115\", \"event\": \"trouble.on\", \"pointAddress\": \"3-3-0\" }";
        sendEvent(troubleOn, bridgeExchange, "jci.tofs.bridge.event.panel.0000082115.points");
    }

    @Test
    public void verifyTroubleOff(){
        final String troubleOff = "{ \"messageId\": \"6ab1048deed15a3e21ec2e3b0e7ba6b9\", \"source\": \"bridge\", \"panelId\": \"0000082115\", \"serial\": \"0000082115\", \"event\": \"trouble.off\", \"pointAddress\": \"3-3-0\" }";
        sendEvent(troubleOff, bridgeExchange, "jci.tofs.bridge.event.panel.0000082115.points");
    }

    private void sendEvent(final String payload, final String exchange, final String routingKey){
        final Message message = new Message(payload.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

}
