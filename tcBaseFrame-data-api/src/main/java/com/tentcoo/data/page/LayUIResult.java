package com.tentcoo.data.page;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/2/7 0007
 */
public class LayUIResult implements Serializable {
    //状态字段名称
    private String  status;
    //状态字段成功值
    private int     code;
    //消息字段
    private String  message;
    //数据
    private List<?> data;
    //数据总数
    private int     count;

    public LayUIResult(List<?> data) {
        this.data = data;
    }

    public LayUIResult() {
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        message = message;
    }
}
