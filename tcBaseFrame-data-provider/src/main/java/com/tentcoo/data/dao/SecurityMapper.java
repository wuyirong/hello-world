package com.tentcoo.data.dao;

import com.tentcoo.data.pojo.Security;

public interface SecurityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_security
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String fSecuritykey);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_security
     *
     * @mbggenerated
     */
    int insert(Security record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_security
     *
     * @mbggenerated
     */
    int insertSelective(Security record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_security
     *
     * @mbggenerated
     */
    Security selectByPrimaryKey(String fSecuritykey);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_security
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Security record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_security
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Security record);
}