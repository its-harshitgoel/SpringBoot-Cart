package com.example.Product.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Product.dto.CartFetchDTO;
import com.example.Product.dto.ProductFetchDTO;
import com.example.Product.models.Cart;
import com.example.Product.models.Product;


import java.util.ArrayList;
import java.util.List;


@Service
public class CartServiceImpl implements CartService {

    private final String url = "https://fakestoreapi.com/carts";
    private final RestTemplate restTemplate = new RestTemplate();

    private Cart convertToCart(CartFetchDTO cartFetchDTO) {
        Cart cart = new Cart();
        cart.setId(cartFetchDTO.getId());
        cart.setUserId(cartFetchDTO.getUserId());
        List<Product> products = new ArrayList<>();
        for (ProductFetchDTO productFetchDTO : cartFetchDTO.getProducts()) {
            Product product = new Product();
            product.setProductId(productFetchDTO.getProductId());
            product.setQuantity(productFetchDTO.getQuantity());
            products.add(product);
        }
        cart.setProducts(products);
        cart.setDate(cartFetchDTO.getDate());
        return cart;
    }

    @Override
    public List<Cart> getAllCarts() {
        CartFetchDTO[] cartFetchDTOS = restTemplate.getForObject(
                url, CartFetchDTO[].class);
        assert cartFetchDTOS != null;
        List<Cart> allCarts = new ArrayList<>();
        for (CartFetchDTO cartFetchDTO : cartFetchDTOS) {
            allCarts.add(convertToCart(cartFetchDTO));
        }
        return allCarts;
    }

    @Override
    public Cart getCartById(Long id) {
        CartFetchDTO cartFetchDTO = restTemplate.getForObject(
                url + "/" + id, CartFetchDTO.class);
        assert cartFetchDTO != null;
        return convertToCart(cartFetchDTO);
    }

    @Override
    public List<Cart> getCartsByUserId(Long userId) {
        CartFetchDTO[] cartFetchDTOS = restTemplate.getForObject(
                url + "/user/" + userId,
                CartFetchDTO[].class);
        assert cartFetchDTOS != null;
        List<Cart> userCarts = new ArrayList<>();
        for (CartFetchDTO cartFetchDTO : cartFetchDTOS) {
            userCarts.add(convertToCart(cartFetchDTO));
        }
        return userCarts;
    }

    @Override
    public List<Cart> getCartsInDateRange(String startDate, String endDate) {
        CartFetchDTO[] cartFetchDTOS = restTemplate.getForObject(
                url + "?startdate=" + startDate + "&enddate=" + endDate,
                CartFetchDTO[].class);
        assert cartFetchDTOS != null;
        List<Cart> dateRangeCarts = new ArrayList<>();
        for (CartFetchDTO cartFetchDTO : cartFetchDTOS) {
            dateRangeCarts.add(convertToCart(cartFetchDTO));
        }
        return dateRangeCarts;
    }

    @Override
    public Cart addCart(Cart cart) {
        CartFetchDTO cartFetchDTO = new CartFetchDTO();
        cartFetchDTO.setUserId(cart.getUserId());
        List<ProductFetchDTO> productFetchDTOS = new ArrayList<>();
        for (Product product : cart.getProducts()) {
            ProductFetchDTO productFetchDTO = new ProductFetchDTO();
            productFetchDTO.setProductId(product.getProductId());
            productFetchDTO.setQuantity(product.getQuantity());
            productFetchDTOS.add(productFetchDTO);
        }
        cartFetchDTO.setProducts(productFetchDTOS);
        cartFetchDTO.setDate(cart.getDate());

        CartFetchDTO response = restTemplate.postForObject(
                url, cartFetchDTO, CartFetchDTO.class);
        assert response != null;
        return convertToCart(response);
    }

    @Override
    public String updateCart(Long id, Cart cart) {
        CartFetchDTO cartFetchDTO = new CartFetchDTO();
        cartFetchDTO.setUserId(cart.getUserId());
        List<ProductFetchDTO> productFetchDTOS = new ArrayList<>();
        for (Product product : cart.getProducts()) {
            ProductFetchDTO productFetchDTO = new ProductFetchDTO();
            productFetchDTO.setProductId(product.getProductId());
            productFetchDTO.setQuantity(product.getQuantity());
            productFetchDTOS.add(productFetchDTO);
        }
        cartFetchDTO.setProducts(productFetchDTOS);
        cartFetchDTO.setDate(cart.getDate());

        restTemplate.put(
                url + "/" + id, cartFetchDTO);
        return "Cart with id " + id + " has been updated";
    }

    @Override
    public String deleteCart(Long id) {
        restTemplate.delete(
                url + "/" + id);
        return "Cart with id " + id + " has been deleted";
    }
}
