package com.sun.demo;

import com.example.demo.rabbitmq.sender.HelloSender;
import com.sun.demo.rabbitmq.sender.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQApplicationTests {

	@Autowired
	private HelloSender helloSender;

	@Autowired
	private HelloSender helloSender2;
	//单一发送
	@Test
	public void hello () throws Exception{
		System.out.println("---------------------------hello----start------------------");
		helloSender.send();
		System.out.println("---------------------------hello-----end-----------------");
	}
	//一对多发送
	@Test
	public void oneToMany () throws Exception{
		System.out.println("---------------------------oneToMany----start------------------");
		for (int i=0;i<50;i++){
			helloSender.send(i);
		}
		System.out.println("---------------------------oneToMany----end------------------");
	}

	//多对多发送
	@Test
	public void manyToMany () throws Exception{
		System.out.println("---------------------------manyToMany----start------------------");
		for (int i=0;i<50;i++){
			helloSender.send(i);
			helloSender2.send(i);
		}
		System.out.println("---------------------------manyToMany----end------------------");
	}
}

