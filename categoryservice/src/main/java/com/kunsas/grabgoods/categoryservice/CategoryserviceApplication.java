package com.kunsas.grabgoods.categoryservice;

import com.kunsas.grabgoods.categoryservice.dto.CategoryConfigInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableConfigurationProperties(value = {CategoryConfigInfoDto.class})
@EnableMongoAuditing
@EnableFeignClients
public class CategoryserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoryserviceApplication.class, args);
	}

}
