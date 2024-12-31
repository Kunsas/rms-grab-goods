package com.kunsas.grabgoods.productservice.service;

import com.kunsas.grabgoods.productservice.constant.ProductConstants;
import com.kunsas.grabgoods.productservice.dto.ProductRequestDto;
import com.kunsas.grabgoods.productservice.dto.ProductResponseDto;
import com.kunsas.grabgoods.productservice.dto.client.*;
import com.kunsas.grabgoods.productservice.entity.Product;
import com.kunsas.grabgoods.productservice.exception.ProductAlreadyExistsException;
import com.kunsas.grabgoods.productservice.exception.ProductNotFoundException;
import com.kunsas.grabgoods.productservice.mapper.ProductMapper;
import com.kunsas.grabgoods.productservice.repository.ProductRepository;
import com.kunsas.grabgoods.productservice.service.client.ICategoryFeignClient;
import com.kunsas.grabgoods.productservice.service.client.IInventoryFeignClient;
import com.kunsas.grabgoods.productservice.service.client.IReviewFeignClient;
import com.mongodb.client.result.UpdateResult;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private ProductRepository productRepository;

    private ICategoryFeignClient categoryFeignClient;

    private IReviewFeignClient reviewFeignClient;

    private IInventoryFeignClient inventoryFeignClient;

    private MongoTemplate mongoTemplate;

    @Override
    public void createProduct(ProductRequestDto productRequestDto) {
        Optional<Product> existingProduct = productRepository.findByName(productRequestDto.getName());
        if (existingProduct.isPresent()) {
            throw new ProductAlreadyExistsException(ProductConstants.PRODUCT_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        } else {
            CategoryLookupRequestDto categoryLookupRequestDto = ProductMapper.mapToCategoryLookupRequestDto(productRequestDto.getCategories());
            List<CategoryResponseDto> categories = categoryFeignClient.getCategoriesByNames(categoryLookupRequestDto);
            List<ReviewResponseDto> reviews = productRequestDto.getReviews();
            Product newProduct = ProductMapper.mapToNewProduct(productRequestDto, categories, reviews);
            productRepository.save(newProduct);
        }
    }

    @Override
    public ProductResponseDto getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(ProductConstants.PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE));
        List<CategoryResponseDto> productCategories = product.getCategories();
        List<String> categories = productCategories.stream().map(CategoryResponseDto::getName).collect(Collectors.toList());
        List<ReviewResponseDto> reviews = reviewFeignClient.getAllReviewsByProductId(id).getBody();
        return ProductMapper.mapToProductResponseDto(product, categories, reviews);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> productResponseDtoList = new ArrayList<ProductResponseDto>();
        if (!products.isEmpty()) {
            productResponseDtoList = products.stream()
                    .map(product -> {
                        List<CategoryResponseDto> productCategories = product.getCategories();
                        List<String> categories = productCategories.stream()
                                .map(CategoryResponseDto::getName)
                                .collect(Collectors.toList());
                        List<ReviewResponseDto> reviews = null;
                        if (product.getReviews().isEmpty()) {
                            reviews = List.of();
                        } else {
                            reviews = reviewFeignClient.getAllReviewsByProductId(product.getId()).getBody();
                        }
                        return ProductMapper.mapToProductResponseDto(product, categories, reviews);
                    })
                    .toList();

        }
        return productResponseDtoList;
    }

    @Override
    public boolean updateProduct(String id, ProductRequestDto productRequestDto) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(ProductConstants.PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE));
        CategoryLookupRequestDto categoryLookupRequestDto = ProductMapper.mapToCategoryLookupRequestDto(productRequestDto.getCategories());
        List<CategoryResponseDto> updatedCategories = categoryFeignClient.getCategoriesByNames(categoryLookupRequestDto);
        List<ReviewResponseDto> reviews = reviewFeignClient.getAllReviewsByProductId(id).getBody();
        Product updatedProduct = ProductMapper.mapToProduct(productRequestDto, updatedCategories, reviews, existingProduct);
        productRepository.save(updatedProduct);
        return true;
    }

    @Override
    public boolean deleteProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(ProductConstants.PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE));
        reviewFeignClient.deleteReviewsByProductId(product.getId());
        inventoryFeignClient.deleteInventory(product.getId());
        productRepository.deleteById(product.getId());
        return true;
    }

    @Override
    public boolean updateCategoryInProduct(String id, CategoryRequestDto categoryRequestDto) {
        Query query = new Query(Criteria.where("categories._id").is(id));
        List<Product> products = mongoTemplate.find(query, Product.class);
        for (Product product : products) {
            product.getCategories().forEach(category -> {
                if (category.getId().equals(id)) {
                    category.setName(categoryRequestDto.getName());
                }
            });
            mongoTemplate.save(product);
        }
        return true;
    }

    @Override
    public boolean deleteCategoryInProduct(String id) {
        Query query = new Query(Criteria.where("categories._id").is(id));
        Update update = new Update().pull("categories", new Query(Criteria.where("_id").is(id)));
        mongoTemplate.updateMulti(query, update, Product.class);
        return true;
    }

    @Override
    public boolean updateReviewInProduct(String productId, String reviewId, ReviewResponseDto reviewResponseDto) {

        Query productQuery = new Query(Criteria.where("_id").is(productId));
        Product product = mongoTemplate.findOne(productQuery, Product.class);

        if (product == null) {
            throw new ProductNotFoundException(ProductConstants.PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        if (product.getReviews().isEmpty()) {
            Update addReviewUpdate = new Update().push("reviews", reviewResponseDto);
            mongoTemplate.updateFirst(productQuery, addReviewUpdate, Product.class);

            return true;
        } else {
            Query updateQuery = new Query(Criteria.where("_id").is(productId).and("reviews._id").is(reviewId));

            Update update = new Update()
                    .set("reviews.$.title", reviewResponseDto.getTitle())
                    .set("reviews.$.description", reviewResponseDto.getDescription())
                    .set("reviews.$.rating", reviewResponseDto.getRating())
                    .set("reviews.$.imageUrl", reviewResponseDto.getImageUrl());

            UpdateResult result = mongoTemplate.updateFirst(updateQuery, update, Product.class);
            return result.getModifiedCount() > 0;
        }
    }

    @Override
    public boolean deleteReviewInProduct(String productId, String reviewId) {
        Query query = new Query(Criteria.where("_id").is(productId));
        Update update = new Update().pull("reviews", new Query(Criteria.where("_id").is(reviewId)));
        UpdateResult result = mongoTemplate.updateFirst(query, update, Product.class);

        return result.getModifiedCount() > 0;
    }

}
