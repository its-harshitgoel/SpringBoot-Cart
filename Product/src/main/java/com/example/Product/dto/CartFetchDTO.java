package com.example.Product.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class CartFetchDTO {
    private Long id;
    private Long userId;
    private String date;
    private List<ProductFetchDTO> products;
}

