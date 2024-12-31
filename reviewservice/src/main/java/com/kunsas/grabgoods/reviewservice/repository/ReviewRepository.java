package com.kunsas.grabgoods.reviewservice.repository;

import com.kunsas.grabgoods.reviewservice.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    Optional<List<Review>> findByProductId(String productId);
    long deleteByProductId(String productId);
    Optional<Review> findByUserIdAndProductIdAndTitle(String userId, String productId, String title);
}
