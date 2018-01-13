package com.tentcoo.data.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.tentcoo.data.api.AnnotationService;
import org.springframework.stereotype.Component;

/**
 * Created by rover on 2018/1/13.
 */
@Component("providerAnnService")
@Service(version="1.0")
public class AnnotationServiceImpl implements AnnotationService {

    @Override
    public String getNameByKey(String key) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", key);
        jsonObject.put("success", true);
        jsonObject.put("message", "通过注解成功调用.");
        return jsonObject.toString();
    }

}
