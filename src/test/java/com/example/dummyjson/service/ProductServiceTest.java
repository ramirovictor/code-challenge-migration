package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    private static MockWebServer mockWebServer;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(9999);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void testGetAllProducts() {
        String mockResponse = """
                {
                    "products": [
                        {"id": 1, "title": "Essence Mascara Lash Princess", "description": "Description", "price": 9.99},
                        {"id": 2, "title": "Eyeshadow Palette with Mirror", "description": "Description", "price": 19.99}
                    ]
                }
                """;

        mockWebServer.enqueue(new MockResponse()
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json"));

        List<Product> products = productService.getAllProducts();

        assertNotNull(products, "Products should not be null");
        assertEquals(2, products.size(), "Expected 2 products in the response");
        assertEquals("Essence Mascara Lash Princess", products.get(0).getTitle(), "First product title should match");
    }

    @Test
    public void testGetProductById() {
        String mockResponse = """
                {
                    "id": 1,
                    "title": "Essence Mascara Lash Princess",
                    "description": "Description",
                    "price": 9.99
                }
                """;

        mockWebServer.enqueue(new MockResponse()
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json"));

        Product product = productService.getProductById(1L);

        assertNotNull(product, "Product should not be null");
        assertEquals(1L, product.getId(), "Product ID should match");
        assertEquals("Essence Mascara Lash Princess", product.getTitle(), "Product title should match");
    }
}
