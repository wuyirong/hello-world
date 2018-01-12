package com.tentcoo.security.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tentcoo.data.api.SecurityService;
import com.tentcoo.data.domain.ErrorResponse;
import com.tentcoo.data.pojo.Security;
import com.tentcoo.utils.JsonUtil;
import com.tentcoo.utils.ReturnCode;

public class UserSecurityInterceptor implements HandlerInterceptor {
	private static ClassPathXmlApplicationContext context = null;

	private ClassPathXmlApplicationContext getContext() {
		if (context == null) {
			synchronized (UserSecurityInterceptor.class) {
				if (context == null) {
					context = new ClassPathXmlApplicationContext(new String[] { "dubbo-services.xml" });
					context.refresh();
					context.start();
				}
			}
		}
		return context;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String secret_key = request.getParameter("secret_key");
		ErrorResponse errorResponse = new ErrorResponse();
		getContext();
		SecurityService securityService = (SecurityService) context.getBean("securityService");
		Security security = securityService.getSecurityByKey(secret_key); // 执行远程方法
		if (security == null) {
			errorResponse.setCode(String.valueOf(ReturnCode.SECURITY_IS_NOT_EXIST_OR_EXPIRED.ordinal()));
			errorResponse.setMsg(ReturnCode.SECURITY_IS_NOT_EXIST_OR_EXPIRED.toString());
			errorResponse.setSub_code(Integer.toHexString(ReturnCode.SECURITY_IS_NOT_EXIST_OR_EXPIRED.getCode()));
			errorResponse.setSub_msg(ReturnCode.SECURITY_IS_NOT_EXIST_OR_EXPIRED.name());
			response.getWriter().write(JsonUtil.convertObject2String(errorResponse));
			return false;
		}
		return true;
	}
}
