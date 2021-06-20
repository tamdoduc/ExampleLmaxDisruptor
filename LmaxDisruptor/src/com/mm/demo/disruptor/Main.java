package com.mm.demo.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.mm.demo.disruptor.handler.BusinessLogicConsumer;
import com.mm.demo.disruptor.handler.JournalConsumer;
import com.mm.demo.disruptor.handler.ReplicateConsumer;
import com.mm.demo.disruptor.handler.Un_MarshallConsumer;

public class Main {

    public static void main(String[] args) {
        Disruptor<Data> disruptor =
                new Disruptor<Data>(
                        Data.EVENT_FACTORY,  // Event Factory –Tạo đối tượng và lưu trữ trong ring buffer khi khơi tạo disruptor

                        1024, //Disruptor bắt buộc giá trị này phải là luỹ thừa của 2.

                        DaemonThreadFactory.INSTANCE, //Thread Factory – Khởi tạo các thread cho các bộ sử lý (processors) cho các event

                        ProducerType.SINGLE, //Chỉ định việc Disruptor làm việc trên một (single) hoặc nhiều (multiple) Producer

                        new BusySpinWaitStrategy()); /*Waiting strategy – Để định nghĩa các chiến thuật (strategy) chờ đợi để sử lý các vấn đề
                         chênh lệch tốc độ gửi và nhận của Producer và Consumer hay còn gọi là "Producer and Consumer problem".*/


        disruptor.handleEventsWith(new ReplicateConsumer(),new JournalConsumer(),new Un_MarshallConsumer())
                                    .then(new BusinessLogicConsumer());
        //Thêm Consumers và thứ tự thực hiện của chúng vào

        disruptor.start();
        Producer producer = new Producer(disruptor.getRingBuffer());

        long timeStartDisruptor = System.currentTimeMillis();

        for (long i = 0; i < 10000; i++  ) {
            producer.pushData(String.valueOf(i));  // truyền dữ liệu vào ringbuffer thông qua producer
        }

        long timEndDisruptor = System.currentTimeMillis();

        disruptor.shutdown();
        System.out.println("Total time disruptor: " +  (timEndDisruptor - timeStartDisruptor));
    }
}