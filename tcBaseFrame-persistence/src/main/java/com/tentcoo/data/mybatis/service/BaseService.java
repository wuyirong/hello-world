package com.tentcoo.data.mybatis.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Service基类
 */
@Transactional(readOnly = true)
public abstract class BaseService {
	
}
