package com.kunsas.grabgoods.reviewservice;

import com.kunsas.grabgoods.reviewservice.dto.ReviewConfigInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableConfigurationProperties(value = {ReviewConfigInfoDto.class})
@EnableMongoAuditing
@EnableFeignClients
public class ReviewserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewserviceApplication.class, args);
	}

}
