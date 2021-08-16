package com.example.productservice.models;
import java.util.Date;
import java.util.UUID;


public class Product {

    private final UUID corrId;

    private String name;

    private Float price;

    private final Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public Product(String name, Float price){
        this.corrId = UUID.randomUUID();
        this.createdAt = new Date();
        this.name = name;
        this.price = price;
    }


    public Product(ProductDTO productDTO){
        this.corrId = UUID.randomUUID();
        this.createdAt = new Date();
        this.name = productDTO.name;
        this.price = productDTO.price;
    }

    public UUID getCorrId() {
        return corrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
