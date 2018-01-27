package com.tentcoo.data.service.impl;

import com.tentcoo.data.api.SysLogService;
import com.tentcoo.data.mapper.SysLogMapper;
import com.tentcoo.data.pojo.SysLog;

import javax.annotation.Resource;

@com.alibaba.dubbo.config.annotation.Service(interfaceClass=SysLogService.class)
//@Service(value = "sysLogService")
public class SysLogServiceImpl implements SysLogService {

	@Resource
	private SysLogMapper sysLogMapper;

	@Override
	public void save(SysLog sysLog) {
		sysLogMapper.save(sysLog);
	}
}
