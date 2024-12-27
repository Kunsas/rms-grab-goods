package com.kunsas.grabgoods.userservice;

import com.kunsas.grabgoods.userservice.dto.UserConfigInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(value = {UserConfigInfoDto.class})
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

}
