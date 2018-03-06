package com.tentcoo.message.provider;

import com.alibaba.fastjson.JSONObject;
import com.tentcoo.message.dto.MessageDto;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * Created by rover on 2018/3/6.
 */
@Component
public class KafkaSender {

    private static Logger log = org.slf4j.LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //发送消息方法
    public void send() {
        MessageDto message = new MessageDto();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString().replaceAll("-",""));
        message.setSendTime(new Date());
        String sendMsg = JSONObject.toJSONString(message);
        log.info("+++++++++++++++++++++  message = {}", sendMsg);
        kafkaTemplate.send("tcKfkMessage", sendMsg);
    }

}
