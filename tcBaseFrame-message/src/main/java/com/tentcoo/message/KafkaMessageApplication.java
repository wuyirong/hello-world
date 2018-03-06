package com.tentcoo.message;

import com.tentcoo.message.provider.KafkaSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by rover on 2018/3/6.
 */
@SpringBootApplication
public class KafkaMessageApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(KafkaMessageApplication.class, args);

        KafkaSender sender = context.getBean(KafkaSender.class);

        for (int i = 0; i < 3; i++) {
            //调用消息发送类中的消息发送方法
            sender.send();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
