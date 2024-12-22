package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ProductService {

    @Value("${dummyjson.base-url}")
    private String baseUrl;

    @Autowired
    private WebClient webClient;

    public List<Product> getAllProducts() {
        ProductResponse response = webClient.get()
                .uri(baseUrl)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();

        return response != null ? response.getProducts() : List.of();
    }

    public Product getProductById(Long id) {
        return webClient.get()
                .uri(baseUrl + "/" + id)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }
}
