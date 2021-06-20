package com.mm.demo.disruptor;

import com.lmax.disruptor.RingBuffer;

public class Producer {

    private final RingBuffer<Data> ringBuffer;

    public Producer(RingBuffer<Data> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(String data) {
        long sequence = ringBuffer.next();

        try {
            Data even = ringBuffer.get(sequence);
            even.setValue(data);
        } finally {
            ringBuffer.publish(sequence);
            System.out.println("Success producer data : " +  data);
        }
    }
}
