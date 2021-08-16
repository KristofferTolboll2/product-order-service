package com.example.productservice.controllers;


import com.example.productservice.exceptions.IllegalParameterException;
import com.example.productservice.models.Product;
import com.example.productservice.models.ProductDTO;
import com.example.productservice.services.ProductService;
import com.example.productservice.util.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Locale;

@RestController()
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/:uuid")
    public ResponseEntity<Product> getProductById(@PathParam("uuid") String uuid){
        var foundProduct = this.productService.getProductById(uuid);
        return new ResponseEntity<Product>(foundProduct, HttpStatus.OK);
    }



    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO){
        var createdProduct = this.productService.createProduct(productDTO);
        return new ResponseEntity<Product>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/:uuid")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO productDTO, @PathParam("uuid") String uuid){
        var updatedProduct = this.productService.updateProduct(uuid, productDTO);
        return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam("sortKey") String sortKey, @RequestParam("sortType") String sortType) throws IllegalParameterException {
        SortType se;
        try{
            se = SortType.fromString(sortType);
        }catch (IllegalArgumentException e){
            StringBuilder stringBuilder = new StringBuilder("Sort Type has to be either ");
            for (SortType value: SortType.values()){
                stringBuilder.append(value.toString()).append(" ");
            }
            throw new IllegalParameterException(stringBuilder.toString());
        }
        var isDescending = sortKey.toLowerCase().equals("desc") || sortKey.toLowerCase().equals("descending");
        var products = this.productService.getProducts(se, isDescending);
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }
}
