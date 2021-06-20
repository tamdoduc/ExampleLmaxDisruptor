package com.mm.demo.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import com.mm.demo.disruptor.Data;

import java.text.MessageFormat;

public class BusinessLogicConsumer implements EventHandler<Data> {
    @Override
    public void onEvent(Data event, long sequence, boolean endOfBatch) throws Exception {
        Processing(event);
    }
    public void Processing(Data event)
    {
        System.out.println(MessageFormat.format("Push message: {0} to Business logic", event.getValue()));
    }
}