package com.tentcoo.data.jpa.service;

import com.tentcoo.data.jpa.dao.BaseDao;
import com.tentcoo.data.jpa.dto.Pager;
import com.tentcoo.data.jpa.dto.QueryResult;
import com.tentcoo.data.jpa.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.io.Serializable;
import java.util.List;

/**
 * BaseService实现层，所有的service实现层都要继承自该类。
 */
@Service
@Transactional
public class BaseServiceImpl<T extends Serializable> implements BaseService<T>{

	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 559268767097149785L;

	/**
	 * 日志记录
	 */
//	protected Logger LOG = LoggerFactory.getLogger(getClass());
	/**
	 * 该方法由子类实现
	 * @return 返回baseDao实例
	 */
//	@Autowired
	//@Resource  解开注释报错
	public BaseDao<T> baseDao;

	public BaseDao<T> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	//	@Resource
//	private RequestInfoDataSource requestInfoDataSource;
	
	@Transactional
	public boolean save(T entity) {
		boolean flag = false;
		try {
			getBaseDao().persist(entity);
			flag = true;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return flag;
	}

	@Transactional
	public boolean update(T entity) {
		boolean flag = false;
		try {
			getBaseDao().merge(entity);
			flag = true;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return flag;
	}

	@Transactional
	public void delete(Serializable... entityIds) {
		try {
			getBaseDao().remove(entityIds);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public T get(Serializable id) {
		try {
			return getBaseDao().find(id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public T LockEntity(Serializable entityId,LockModeType lockModeType) {
		try {
			return getBaseDao().find(entityId, lockModeType);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public boolean isEntityExist(Serializable entityId) {
		if (null == entityId)
			return false;
		try {
			return getBaseDao().isEntityExist(entityId);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryResult<T> query(Pager pager) {
		try {
			QueryResult<T> qr = null;
			if (null != pager) {
				qr = getBaseDao().query(pager.getOffset(), pager.getSize());
			}
			return qr;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public List<T> all() {
		try {
			return getBaseDao().list(null);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Transactional
	public int updateSQL(String sql, Object... params){
		int res = 0;
		try {
			res = getBaseDao().updateSQL(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**根据jdbc来查询List*/
	public List queryListByJdbc(String sql, String[] params){
		try {
			return getBaseDao().queryListByJdbc(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**根据jdbc来查询Map*/
	public java.util.Map queryMapByJdbc(String sql, String[] params){
		try {
			return getBaseDao().queryMapByJdbc(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
