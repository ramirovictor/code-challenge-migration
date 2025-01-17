package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductResponse;
import com.example.dummyjson.exception.ResourceNotFoundException;
import com.example.dummyjson.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.function.client.WebClientException;

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

        if (response == null) {
            throw new ResourceNotFoundException("Failed to retrieve products");
        }
        
        return response.getProducts();
    }

    public Product getProductById(Long id) {
        try {
            return webClient.get()
                    .uri(baseUrl + "/" + id)
                    .retrieve()
                    .bodyToMono(Product.class)
                    .block();
        } catch (WebClientResponseException.NotFound e) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        } catch (WebClientException e) {
            throw new ServiceException("Error fetching product with id: " + id, e);
        }
    }
}
