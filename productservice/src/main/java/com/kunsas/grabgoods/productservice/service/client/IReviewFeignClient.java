package com.kunsas.grabgoods.productservice.service.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("reviewservice")
public interface IReviewFeignClient {
}
