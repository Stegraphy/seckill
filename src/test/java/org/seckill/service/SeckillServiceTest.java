package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
                        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetSeckillList() {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}",list);
        //日志输出：Closing non transactional SqlSession
    }

    @Test
    public void testGetById() {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void testExportSeckillUrl() {
        long id =1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}",exposer);
    }

    @Test
    public void testExecuteSeckill() {
        //重复执行报错：org.seckill.exception.RepeatKillException: seckill repeated
        long id = 1000;
        long phone = 13551566296L;
        String md5 = "8ae6eedcd2e06881b83612109547ed49";

/*
        try {
            SeckillExecution execution =  seckillService.executeSeckill(id,phone,md5);
            logger.info("result={}",execution);
        }catch (RepeatKillException e){
            logger.error(e.getMessage(),e);
        }catch (SeckillCloseException e){
            logger.error(e.getMessage());
        }
*/


        //报错org.seckill.exception.SeckillException: seckillId data review    (md5判断时应该取反）
        //测试结果
        //result=SeckillExecution{seckillId=1000,
        // state=1, stateInfo='秒杀成功',
        // successKilled=SuccessKilled{seckillId=1000,
        // userPhone=13551566296, state=0, createTime=Sun
    }

    //集成测试代码玩完整逻辑，注意可重复执行
    @Test
    public void testSeckillLogc() throws Exception{
        long id = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if(exposer.isExposed()) {
            logger.info("exposer={}",exposer);
            long phone = 13551566296L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution execution =  seckillService.executeSeckill(id,phone,md5);
                logger.info("result={}",execution);
            }catch (RepeatKillException e) {
                logger.error(e.getMessage(), e);
            }catch (SeckillCloseException e) {
                logger.error(e.getMessage(), e);
            }
        }else{
            //秒杀未开启
            logger.warn("exposer={}",exposer);
        }

    }
}