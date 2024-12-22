package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ProductControllerTest {

    @Autowired
    private WebTestClient webTestClient;

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

        webTestClient.get()
                .uri("/api/products")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class)
                .consumeWith(response -> {
                    var products = response.getResponseBody();
                    assertNotNull(products, "Products should not be null");
                    assertEquals(2, products.size(), "Expected 2 products in the response");
                });
    }


    @Test
    public void testGetProductById() {
        String mockResponse = """
                    {"id": 2, "title": "Eyeshadow Palette with Mirror", "description": "Description", "price": 19.99}
                """;

        mockWebServer.enqueue(new MockResponse()
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json"));

        webTestClient.get()
                .uri("/api/products/2")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class)
                .consumeWith(response -> {
                    var product = response.getResponseBody();
                    assertNotNull(product, "Product should not be null");
                    assertEquals(2L, product.getId(), "Product ID should match");
                    assertEquals("Eyeshadow Palette with Mirror", product.getTitle(), "Product title should match");
                });
    }
}
