package com.sun.demo;

import com.example.demo.dto.HUSER;
import com.example.demo.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	public HelloService helloService;
	@Test
	public void contextLoads() {
		HUSER huser = helloService.select(new Long(1));
		System.out.println("huser.toString()="+huser.toString());
	}

}

