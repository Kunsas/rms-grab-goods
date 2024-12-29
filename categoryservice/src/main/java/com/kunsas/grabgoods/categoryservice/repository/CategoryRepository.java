package com.kunsas.grabgoods.categoryservice.repository;

import com.kunsas.grabgoods.categoryservice.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    Optional<Category> findByName(String name);
    Optional<List<Category>> findByNameIn(List<String> names);
}
