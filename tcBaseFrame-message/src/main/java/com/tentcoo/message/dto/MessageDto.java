package com.tentcoo.message.dto;

import java.util.Date;

/**
 * Created by rover on 2018/3/6.
 */
//@Data
public class MessageDto {

    /**id*/
    private Long id;

    /**消息*/
    private String msg;

    /**时间戳*/
    private Date sendTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

}
