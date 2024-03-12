// package com.example.Product.service;

// import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;

// import com.example.Product.dto.FakeProductDto;

// @Service
// public class ProductService {

//     RestTemplate restTemplate = new RestTemplate();
//     String url = "https://fakestoreapi.com";

//     /**
//      * Retrieves all products.
//      * 
//      * @return String representing all products
//      */
//     public FakeProductDto[] getAllProducts() {
//         var response = restTemplate.getForObject(url + "/products/", FakeProductDto[].class);
//         return response;
//     }

//     /**
//      * Retrieves a product by its ID.
//      * 
//      * @param id The ID of the product to retrieve
//      * @return String representing the product with the given ID
//      */
//     public FakeProductDto getProductById(Long id) {
//         // In service, assume that all inputs are passed by parameter, no need for
//         // @PathVariable
//         var response = restTemplate.getForObject(url + "/products/" + id, FakeProductDto.class);
//         return response;
//     }

//     public FakeProductDto getACategory(String category) {
//         var response = restTemplate.getForObject(url + "/products/categories/" + category, FakeProductDto.class);
//         return response;
//     }

//     public FakeProductDto getAllCategory(String category) {
//         var response = restTemplate.getForObject(url + "/products/categories/", FakeProductDto.class);
//         return response;
//     }
// }
