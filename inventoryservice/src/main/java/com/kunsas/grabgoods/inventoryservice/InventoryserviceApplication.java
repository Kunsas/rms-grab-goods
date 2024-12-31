package com.kunsas.grabgoods.inventoryservice;

import com.kunsas.grabgoods.inventoryservice.dto.InventoryConfigInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(value = {InventoryConfigInfoDto.class})
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class InventoryserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryserviceApplication.class, args);
	}

}
