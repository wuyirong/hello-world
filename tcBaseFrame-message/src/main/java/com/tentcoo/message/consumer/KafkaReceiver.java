package com.tentcoo.message.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by rover on 2018/3/6.
 */
@Component
public class KafkaReceiver {

    private static Logger log = org.slf4j.LoggerFactory.getLogger(KafkaReceiver.class);

    @KafkaListener(topics = {"tcKfkMessage"})
    public void listen(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            log.info("----------------- record =" + record);
            log.info("------------------ message =" + message);
        }

    }

}
