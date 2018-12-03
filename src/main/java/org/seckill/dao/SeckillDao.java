package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

public interface SeckillDao {

    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return 如果影响行数>1,标识更新的记录行数
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     *根据id查询秒杀对象
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     * @param offet 表示偏移量
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit")int limit);

}


/*
<!--<?xml version="1.0" encoding="UTF-8"?>-->
<!--<beans xmlns="http://www.springframework.org/schema/beans"-->
       <!--xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"-->
       <!--xmlns:context="http://www.springframework.org/schema/context"-->
       <!--xmlns:util="http://www.springframework.org/schema/util"-->
       <!--xsi:schemaLocation="http://www.springframework.org/schema/beans-->
        <!--http://www.springframework.org/schema/beans/spring-beans.xsd-->
         <!--http://www.springframework.org/schema/context-->
          <!--http://www.springframework.org/schema/context/spring-context.xsd-->
          <!--http://www.springframework.org/schema/util-->
           <!--http://www.springframework.org/schema/util/spring-util.xsd">-->

    <!--&lt;!&ndash;配置整合MyBatis&ndash;&gt;-->
    <!--&lt;!&ndash;1:配置数据库相关参数-->
    <!--properties的属性：${url}&ndash;&gt;-->
    <!--<context:property-placeholder location="classpath:jdbc.properties"/>-->

    <!--&lt;!&ndash; 2数据库连接池&ndash;&gt;-->
    <!--<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">-->
        <!--&lt;!&ndash;配置连接池属性&ndash;&gt;-->
        <!--<property name="driverClass" value="${driver}"/>-->
        <!--<property name="jdbcUrl" value="${url}"/>-->
        <!--<property name="user" value="${username}"/>-->
        <!--<property name="password" value="${password}"/>-->
 */