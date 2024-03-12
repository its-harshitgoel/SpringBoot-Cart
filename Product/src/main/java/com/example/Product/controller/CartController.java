package com.example.Product.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.Product.models.Cart;
import com.example.Product.service.CartService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@RestController
@RequestMapping("/carts")
public class CartController {

    private CartService cartService;
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping("/status")
    public String status() {
        return "Cart Service is up and running";
    }

    @GetMapping("")
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/cart/{id}")
    public Cart getCartById(@PathVariable("id") Long id){
        return cartService.getCartById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Cart> getCartsByUserId(@PathVariable("userId") Long userId){
        return cartService.getCartsByUserId(userId);
    }

    @GetMapping("/")
    public List<Cart> getCartsInDateRange(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
        return cartService.getCartsInDateRange(startDate, endDate);
    }


    @PostMapping("")
    public Cart addCart(@RequestBody Cart cart){
        return cartService.addCart(cart);
    }

    @PutMapping("/cart/{id}")
    public String updateCart(@PathVariable("id") Long id, @RequestBody Cart cart){
        return cartService.updateCart(id, cart);
    }

    @DeleteMapping("/cart/{id}")
    public String deleteCart(@PathVariable("id") Long id){
        return cartService.deleteCart(id);
    }
}