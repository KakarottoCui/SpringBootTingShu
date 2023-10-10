package com.ivo.mas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ivo.mas.mapper")
public class MyBusApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBusApplication.class, args);
	}

}
