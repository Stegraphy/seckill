package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合junit启动时加载springIOC容器
 * spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;


    @Test
    public void testQueryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
        /*
        1000元秒杀IphonesXR
        Seckill{seckillId=1000, name='1000元秒杀IphonesXR',
         number=100, startTime=Thu Nov 01 00:00:00 CST 2018,
         endTime=Fri Nov 02 00:00:00 CST 2018,
          createTime=Thu Nov 29 20:31:05 CST 2018}

         */
    }

    @Test
    public void testQueryAll() throws Exception {
        /*
        Caused by: org.apache.ibatis.binding.BindingException:
        Parameter 'offset' not found. Available parameters are [0, 1, param1, param2]

        //    List<Seckill> queryAll(int offet, int limit);
        // java没有保存形参的记录queryAll(int offet, int limit)->queryAll(agr0,arg1)
        //

         */
        List<Seckill> seckills= seckillDao.queryAll(0,100);
        for(Seckill seckill : seckills) {
            System.out.println(seckill);
        }

        /*
        Seckill{seckillId=1000, name='1000元秒杀IphonesXR', number=100, startTime=Thu Nov 01 00:00:00 CST 2018, endTime=Fri Nov 02 00:00:00 CST 2018, createTime=Thu Nov 29 20:31:05 CST 2018}
        Seckill{seckillId=1001, name='500元秒杀Ipod', number=200, startTime=Thu Nov 01 00:00:00 CST 2018, endTime=Fri Nov 02 00:00:00 CST 2018, createTime=Thu Nov 29 20:31:05 CST 2018}
        Seckill{seckillId=1002, name='300元秒杀NIKE AJ', number=300, startTime=Thu Nov 01 00:00:00 CST 2018, endTime=Fri Nov 02 00:00:00 CST 2018, createTime=Thu Nov 29 20:31:05 CST 2018}
        Seckill{seckillId=1003, name='200元秒杀Linux 课程', number=400, startTime=Thu Nov 01 00:00:00 CST 2018, endTime=Fri Nov 02 00:00:00 CST 2018, createTime=Thu Nov 29 20:31:05 CST 2018}

         */
    }

    @Test
    public void testReduceNumber() throws Exception {
        long id = 1000L;
        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(id,killTime);
        System.out.println("updateCount=" + updateCount);
    }

}