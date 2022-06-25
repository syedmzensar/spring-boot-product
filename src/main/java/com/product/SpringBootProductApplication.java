package com.product;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
=======
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
>>>>>>> branch 'master' of https://github.com/syedmzensar/spring-boot-product.git
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.product")
<<<<<<< HEAD
@EnableEurekaClient
@EnableFeignClients
public class SpringBootProductApplication {
=======
public class SpringBootProductApplication extends SpringBootServletInitializer {
>>>>>>> branch 'master' of https://github.com/syedmzensar/spring-boot-product.git

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProductApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return super.configure(builder);
	}

}
