package com.example.productservice.models;

import java.util.List;

public class OrderDTO {

    public String buyerEmail;

    public List<Product> items;

    public OrderDTO(String buyerEmail, List<Product> products) {
        this.buyerEmail = buyerEmail;
        this.items = products;
    }
}
