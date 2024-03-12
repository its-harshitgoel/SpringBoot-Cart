package com.example.Product.service;

import java.util.List;

import com.example.Product.models.Cart;

public interface CartService {
    public List<Cart> getAllCarts();
    public Cart getCartById(Long id);
    public List<Cart> getCartsByUserId(Long userId);
    public List<Cart> getCartsInDateRange(String startDate, String endDate);
    public Cart addCart(Cart cart);
    public String updateCart(Long id, Cart cart);
    public String deleteCart(Long id);
}