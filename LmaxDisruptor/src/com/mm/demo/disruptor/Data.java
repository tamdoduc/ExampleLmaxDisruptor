package com.mm.demo.disruptor;

import com.lmax.disruptor.EventFactory;

public class Data {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public final static EventFactory<Data> EVENT_FACTORY = new EventFactory<Data>() {
        public Data newInstance() {
            return new Data();
        }
    };
}


