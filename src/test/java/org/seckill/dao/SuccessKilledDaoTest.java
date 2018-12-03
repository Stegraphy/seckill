package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;
    @Test
    public void TestInsertSuccessKilled() {
        long id = 1001L;
        long phone = 13516607296L;
        int insertCount = successKilledDao.insertSuccessKilled(id,phone);
        System.out.println("insertCount=" + insertCount);
    }

    @Test
    public void queryByIdWithSeckill() {
        long id = 1001L;
        long phone = 13516607296L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id,phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
        /*
        SuccessKilled{seckillId=1000,
        userPhone=13516607296,
         state=-1,
         createTime=Sat Dec 01 10:33:06 CST 2018}
        Seckill{seckillId=1000,
        name='1000元秒杀IphonesXR',
         number=99,
         startTime=Sat Dec 01 10:51:55 CST 2018,
         endTime=Sun Dec 02 00:00:00 CST 2018,
          createTime=Thu Nov 29 20:31:05 CST 2018}

         */
    }
}