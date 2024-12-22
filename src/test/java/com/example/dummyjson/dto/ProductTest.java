package com.example.dummyjson.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    public void testGetAndSetters() {
        Long expectedId = 1L;
        String expectedTitle = "A dummy title";
        String expectedDescription = "A dummy description";
        Double expectedPrice = 2.1;

        Product product = new Product();
        product.setId(expectedId);
        product.setTitle(expectedTitle);
        product.setDescription(expectedDescription);
        product.setPrice(expectedPrice);

        assertEquals(expectedId, product.getId());
        assertEquals(expectedTitle, product.getTitle());
        assertEquals(expectedDescription, product.getDescription());
        assertEquals(expectedPrice, product.getPrice());
    }
}
