package com.webMagic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@Configuration
@SpringBootApplication
@EnableWebMvc
@MapperScan(basePackages={"com.webMagic.config","com.webMagic.dao","com.webMagic.controller","com.webMagic.model","com.webMagic.service"})
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
