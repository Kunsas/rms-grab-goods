package com.kunsas.grabgoods.productservice;

import com.kunsas.grabgoods.productservice.dto.ProductConfigInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableConfigurationProperties(value = {ProductConfigInfoDto.class})
@EnableMongoAuditing
@EnableFeignClients
public class ProductserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductserviceApplication.class, args);
	}

}
