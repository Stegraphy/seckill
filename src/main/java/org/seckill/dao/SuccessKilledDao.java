package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;



public interface SuccessKilledDao {

    /**
     * 根据购买明细，可过滤重复
     * @param seckillId
     * @param userPhone
     * @return
     * 插入的结果集数量（行数）
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone")long userPhone);

    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone")long userPhone);
}


/*
 SELECT
        sk.seckill_id,
        sk.user_phone,
        sk.create_time,
        sk.state,
        s.seckill_id ,
        s.name,
        s.number ,
        s.start_time,
        s.end_time ,
        s.create_time
        FROM  success_seckill sk
        INNER JOIN seckill s on sk.seckill_id=s.seckill_id
        WHERE sk.seckill_id=1000 AND sk.user_phone=13516607296
 */