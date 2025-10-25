package com.udemy.ecommerce.service;

import com.udemy.ecommerce.dao.ProductRepository;
import com.udemy.ecommerce.dtos.ProductDto;
import com.udemy.ecommerce.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> {
                    String categoryName = null;
                    if (product.getCategory() != null) {
                        categoryName = product.getCategory().getCategoryName();
                    }

                    return new ProductDto(
                            product.getId(),                 // Long
                            product.getSku(),                // String
                            product.getName(),               // String
                            product.getDescription(),        // String
                            product.getUnitPrice(),          // BigDecimal
                            product.getImageUrl(),           // String
                            product.isActive(),              // boolean
                            product.getUnitsInStock(),       // int
                            product.getDateCreated(),        // Date
                            product.getLastUpdated(),        // Date
                            categoryName                     // String
                    );
                })
                .collect(Collectors.toList());
    }
    }

