package com.tentcoo.data.service.impl;

import javax.annotation.Resource;

import com.tentcoo.data.api.SecurityService;
import com.tentcoo.data.mapper.SecurityMapper;
import com.tentcoo.data.pojo.Security;
import org.springframework.stereotype.Service;

@Service(value = "securityService")
public class SecurityServiceImpl implements SecurityService {

	@Resource
	private SecurityMapper securityMapper;

	@Override
	public Security getSecurityByKey(String key) {
		Security security = securityMapper.selectByPrimaryKey(key);
		return security;
	}
}
