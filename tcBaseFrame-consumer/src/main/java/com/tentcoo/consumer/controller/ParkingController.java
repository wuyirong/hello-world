package com.tentcoo.consumer.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.ImportResource;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.tentcoo.data.api.AnnotationService;
import com.tentcoo.data.api.UserService;
import com.tentcoo.data.entity.UserEntity;
import com.tentcoo.data.pojo.Security;
import com.tentcoo.log.annotation.LogAnnotation;
import com.tentcoo.log.util.MyLog;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.tentcoo.data.api.SecurityService;
import com.tentcoo.consumer.service.InfrastructDeal;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@ImportResource({ "classpath:applicationContext.xml", "dubbo-services.xml" })
//@EnableAutoConfiguration
public class ParkingController {//extends WebMvcConfigurerAdapter

	private MyLog logger = MyLog.getLog(ParkingController.class);

	@Resource
	private Map<String, InfrastructDeal> posDataServiceMap;
	@Resource
	private SecurityService securityService;

	@Reference//(version="1.0")
	private AnnotationService annotationService;
	@Reference
	private UserService userService;

	//@Value("#{propertyConfigurer['jdbc.type']}")
	private String jdbcType;

	@RequestMapping(value = "/getSession", method = RequestMethod.GET)
	public void getSession(HttpServletRequest request, HttpServletResponse response) {
		try {
			/* 得到指定的head头的值 */
			// String headValue = request.getHeader("host");
			// System.out.println(headValue);
			// System.out.println("-------------------------------------------------");
			// Enumeration<String> e = request.getHeaders("Accept-Encoding");
			// while (e.hasMoreElements()) {
			// headValue = (String) e.nextElement();
			// String value = request.getHeader(headValue);
			// System.out.println(headValue + "=" + value);
			// }
			// System.out.println("-------------------------------------------------");
			// e = request.getHeaderNames();
			// while (e.hasMoreElements()) {
			// String name = (String) e.nextElement();
			// String value = request.getHeader(name);
			// System.out.println(name + " = " + value);
			//
			// }
			HttpSession session = request.getSession();
			session.setAttribute("username", "chubin");
			response.getWriter().write(
					"this is node3, port:8082,address:" + InetAddress.getLocalHost() + ",sessionid:" + session.getId());
		} catch (IOException ex) {
		}
	}

	@RequestMapping(value = "/getUserName", method = RequestMethod.GET)
	public void getUserName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			HttpSession session = request.getSession();
			response.getWriter()
					.write("this is node3, port:8082,address:" + InetAddress.getLocalHost() + ",attribute:"
							+ (session == null ? "no session found"
									: (session.getAttribute("username") == null ? "no attribute found"
											: session.getAttribute("username").toString())));
		} catch (IOException ex) {
			throw ex;
		}
	}

	@RequestMapping(value = "/syncParkingBasicData", method = RequestMethod.POST)
	public void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getParameter("method");
		try {
			InfrastructDeal ideal = posDataServiceMap.get(method);
			String responseResult = "";
			if (ideal != null) {
				responseResult = ideal.accept(request);
			}
			response.getWriter().write(responseResult);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@RequestMapping(value = "/getAnnService", method = RequestMethod.POST)
	@ResponseBody
	@LogAnnotation(value="##########调用注解接口.")
	public String getAnnService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String secretKey = request.getParameter("secret_key");
		try {
//			AnnotationService handler = (AnnotationService)MyLogProxy.getInstance(annotationService);
//			String responseResult = handler.getNameByKey(secretKey);
			//MyBatis
			String responseResult = annotationService.getNameByKey(secretKey);
			Security security = securityService.getSecurityByKey(secretKey);
			logger.info("security="+JSON.toJSONString(security));
			//System.out.println("res="+responseResult);
			//jpa-hibernate
			List<UserEntity> userList = userService.all();
			logger.info("######userList1="+ JSON.toJSONString(userList)+",size="+userList.size());
			UserEntity userEntity = new UserEntity();
			//userEntity.setId(UUID.randomUUID().toString().replace("-",""));
			userEntity.setAddress("测试" + RandomStringUtils.randomAlphabetic(10));
			userEntity.setSex("1");
			userEntity.setBirthDay(new Date());
			userEntity.setUserName("uName"+RandomStringUtils.randomAlphabetic(10));
			userService.save(userEntity);
			userList = userService.all();
			logger.info("######userList2="+ JSON.toJSONString(userList)+",size="+userList.size());
			return responseResult;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "{success:false,message:'exception'}";
	}

}
