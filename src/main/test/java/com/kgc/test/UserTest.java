package com.kgc.test;

import com.kgc.pojo.Bill;
import com.kgc.pojo.User;
import com.kgc.service.BillService;
import com.kgc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserTest {
    @Resource
    UserService service;
    @Test
    public void test01(){
        User user = service.selectByCode("admin", "1234567");
        System.out.println(user);
    }
    @Resource
    BillService billService;
    @Test
    public void test02(){
        /*List<Bill> bills =billService.selectAll() ;
        System.out.println(bills);*/
    }
}
