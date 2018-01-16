package com.tentcoo.data.api;

import com.tentcoo.log.annotation.LogAnnotation;

/**
 * Created by rover on 2018/1/12.
 */
public interface AnnotationService {

    @LogAnnotation(value = "通过动态代理和注解,来调用getNameByKey,并生成日志信息.")
    public String getNameByKey(String key);

}
