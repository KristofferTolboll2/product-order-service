package com.example.productservice.services;

import com.example.productservice.models.Product;
import com.example.productservice.models.ProductDTO;
import com.example.productservice.repositories.ProductRepository;
import com.example.productservice.util.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;


    public Product getProductById(String uuid){
        try {
            return this.productRepository.getByKey(uuid);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public Product updateProduct(String uuid, ProductDTO updatedProduct){
        try {
            return this.productRepository.update(uuid, updatedProduct);
        }catch(Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
          }
    }

    public Product createProduct(ProductDTO productDTO) {
        return this.productRepository.create(productDTO);
    }

    public List<Product> getProducts(SortType sortType, Boolean isDescending){
        return this.productRepository.getAll(sortType, isDescending);
    }



}
