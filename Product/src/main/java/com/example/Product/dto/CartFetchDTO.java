package com.example.Product.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartFetchDTO {
    private Long id;
    private Long userId;
    private String date;
    private List<ProductFetchDTO> products;

    // Explicitly defined getter and setter for products
    public List<ProductFetchDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductFetchDTO> products) {
        this.products = products;
    }
}
