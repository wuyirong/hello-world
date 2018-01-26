package com.tentcoo.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.springframework.http.converter.json.MappingJacksonValue;

public class JsonUtil {
	private static ObjectMapper mapper = null;
	private static Boolean singleInstance = true;

	/**
	 * @author neo
	 *            是否为单例模式
	 * @return
	 */
	private static void getObjectMapperInstance() {
		if (singleInstance) {
			if (mapper == null) {
				synchronized (JsonUtil.class) {
					if (mapper == null) {
						mapper = new ObjectMapper();
					}
				}
			}
		} else {
			mapper = new ObjectMapper();
		}
	}

	public static String convertObject2String(Object arg0) throws JsonProcessingException {
		getObjectMapperInstance();
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true); // 序列化默认以类名为根元素
		return mapper.writeValueAsString(arg0);
	}

	public static <T> T convertString2Object(String content, Class<T> arg0)
			throws JsonParseException, JsonMappingException, IOException {
		getObjectMapperInstance();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true); // 序列化默认以类名为根元素
		T t = mapper.readValue(content, arg0);
		return t;
	}

	public static JsonNode getJsonNodeByKey(String content, String key) throws JsonProcessingException, IOException {
		getObjectMapperInstance();
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);// 序列化默认以类名为根元素
		JsonNode jsonNode = mapper.readTree(content);
		return jsonNode.findValue(key);
	}

	/**
	 * 将对象转换成json字符串。
	 * @param data
	 * @return
	 */
	public static String objectToJson(Object data) {
		try {
			getObjectMapperInstance();
			String string = mapper.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json结果集转化为对象
	 * @param jsonData json数据
	 * @param beanType 对象中的object类型
	 * @return
	 */
	public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
		try {
			getObjectMapperInstance();
			T t = mapper.readValue(jsonData, beanType);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json数据转换成pojo对象list
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
		JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
		try {
			getObjectMapperInstance();
			List<T> list = mapper.readValue(jsonData, javaType);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static MappingJacksonValue jsonp(Object value, String callback){
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(value);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}

}
