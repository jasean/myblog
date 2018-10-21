package com.hbwh.xj.myblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan(basePackages={"com.hbwh.xj.myblog.dao"})
//@MapperScan("com.hbwh.xj.myblog.dao")
@Controller
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
	
	@RequestMapping("/home")
	public String appHome(){
		return "/home";
	}



}

