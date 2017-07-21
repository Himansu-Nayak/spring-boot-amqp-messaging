package com.org.spring.boot.amqp.processor.bridge;

import com.org.spring.boot.amqp.message.devices.ActiveDevicesCountMessageData;
import com.org.spring.boot.amqp.processor.Event;
import com.org.spring.boot.amqp.processor.Processor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ActiveDevicesCountProcessor extends Processor<ActiveDevicesCountMessageData> {

    public ActiveDevicesCountProcessor() {
        super(ActiveDevicesCountMessageData.class);
    }

    @Override
    public void process(final String message) {
        super.process(message);
    }

    @Override
    protected Event buildEvent(final ActiveDevicesCountMessageData activeDevicesCountMessageData) {
        return new Event("device.active.count", activeDevicesCountMessageData.getSerial());
    }

}
