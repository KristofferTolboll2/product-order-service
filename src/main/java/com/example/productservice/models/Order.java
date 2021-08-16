package com.example.productservice.models;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Order {

    private final UUID orderId;

    private String buyerEmail;

    private float price;

    private List<Product> items;

    private Date createdAt;


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Order(OrderDTO order){
        this.orderId = UUID.randomUUID();
        this.buyerEmail = order.buyerEmail;
        this.items = order.items;
        this.price = items.stream().map(Product::getPrice).reduce(0F, Float::sum);
        this.createdAt = new Date();
    }

    public Order(String buyerEmail, List<Product> items) {
        this.orderId = UUID.randomUUID();
        this.buyerEmail = buyerEmail;
        this.items = items;
        this.price = items.stream().map(Product::getPrice).reduce(0F, Float::sum);
        this.createdAt = new Date();
    }

    public Float calculatePrice(float amountToChange, boolean isAddition){
        if(isAddition) this.setPrice(this.price + amountToChange);
        else this.setPrice(this.price - amountToChange);
        return this.price;
    }

    public void addProduct(Product entry){
        this.items.add(entry);
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public UUID getOrderId() {
        return orderId;
    }
}
