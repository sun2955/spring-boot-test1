package com.example.demo;

import com.example.demo.dto.HUSER;
import com.example.demo.model.BaseDic;
import com.example.demo.redis.service.RedisService;
import com.example.demo.service.HelloService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

	@Autowired
	private RedisService redisservice;


	@Test
	public void contextLoads() {
		List<BaseDic> test = redisservice.getList("dic:faultLevel");
		for (BaseDic xx: test) {
			System.out.println(xx.getDicCode());
			System.out.println(xx.getDicId());
			System.out.println(xx.getDicCreateTime());
			System.out.println(xx.getDicName());
			System.out.println("----------------------");
		}
		//Object test = redisservice.getList("name");
		//String test =redisservice.getString("name");
		System.out.println("test.size()="+test.toString());
	}

}

