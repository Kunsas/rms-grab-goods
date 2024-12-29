package com.kunsas.grabgoods.productservice.service;

import com.kunsas.grabgoods.productservice.constant.ProductConstants;
import com.kunsas.grabgoods.productservice.dto.ProductRequestDto;
import com.kunsas.grabgoods.productservice.dto.ProductResponseDto;
import com.kunsas.grabgoods.productservice.dto.client.CategoryLookupRequestDto;
import com.kunsas.grabgoods.productservice.dto.client.CategoryResponseDto;
import com.kunsas.grabgoods.productservice.entity.Product;
import com.kunsas.grabgoods.productservice.exception.ProductAlreadyExistsException;
import com.kunsas.grabgoods.productservice.exception.ProductNotFoundException;
import com.kunsas.grabgoods.productservice.mapper.ProductMapper;
import com.kunsas.grabgoods.productservice.repository.ProductRepository;
import com.kunsas.grabgoods.productservice.service.client.ICategoryFeignClient;
import lombok.AllArgsConstructor;
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

    @Override
    public void createProduct(ProductRequestDto productRequestDto) {
        Optional<Product> existingProduct = productRepository.findByName(productRequestDto.getName());
        if (existingProduct.isPresent()) {
            throw new ProductAlreadyExistsException(ProductConstants.PRODUCT_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        } else {
            CategoryLookupRequestDto categoryLookupRequestDto = ProductMapper.mapToCategoryLookupRequestDto(productRequestDto.getCategories());
            List<CategoryResponseDto> categories = categoryFeignClient.getCategoriesByNames(categoryLookupRequestDto);
            Product newProduct = ProductMapper.mapToNewProduct(productRequestDto, categories);
            productRepository.save(newProduct);
        }
    }

    @Override
    public ProductResponseDto getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(ProductConstants.PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE));
        List<CategoryResponseDto> productCategories = product.getCategories();
        List<String> categories = productCategories.stream().map(CategoryResponseDto::getName).collect(Collectors.toList());
        return ProductMapper.mapToProductResponseDto(product,categories);
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

                        return ProductMapper.mapToProductResponseDto(product, categories);
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
        Product updatedProduct = ProductMapper.mapToProduct(productRequestDto, updatedCategories, existingProduct);
        productRepository.save(updatedProduct);
        return true;
    }

    @Override
    public boolean deleteProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(ProductConstants.PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE));
        productRepository.deleteById(product.getId());
        return true;
    }
}
