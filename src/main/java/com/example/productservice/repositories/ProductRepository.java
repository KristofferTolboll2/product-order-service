package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import com.example.productservice.models.ProductDTO;
import com.example.productservice.util.SortType;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class ProductRepository extends InMemoryDatastore<Product> implements BaseRepository<Product, String, ProductDTO>  {

    ProductRepository() {
        super();
    }

    @Override
    public Product create(ProductDTO entry) {
        var createdProduct = new Product(entry);
        this.inMemoryDB.add(createdProduct);
        return createdProduct;
    }

    @Override
    public List<Product> getAll(SortType sort, Boolean isAscending) {
       switch(sort) {
           case CHRONOLOGICAL: {
               System.out.println(this.inMemoryDB);
               if(isAscending) this.inMemoryDB.sort(Comparator.comparing(Product::getCreatedAt));
               else this.inMemoryDB.sort(Comparator.comparing(Product::getCreatedAt).reversed());
               return this.inMemoryDB;
           }
           case ALPHABETIC: {
               if(isAscending) this.inMemoryDB.sort(Comparator.comparing(Product::getName));
               else this.inMemoryDB.sort(Comparator.comparing(Product::getName).reversed());
               return this.inMemoryDB;
           }
           default: {
               return this.inMemoryDB;
           }
       }
    }

    @Override
    public Product getByKey(String key) throws Exception {
        var foundEntry = this.inMemoryDB.stream().filter(entry -> entry.getCorrId().toString().equals(key)).collect(Collectors.toList()).get(0);
        if(foundEntry == null){
            //Custom expception should be implemented in the future to give more transparency to the end user of the API
            throw new Exception("Product not found");
        }
        return foundEntry;
    }

    @Override
    public Product update(String key, ProductDTO entry) throws Exception {
        var foundEntry = this.inMemoryDB.stream().filter(e -> e.getCorrId().toString().equals(key)).collect(Collectors.toList()).get(0);
          if(foundEntry == null) {
              throw new Exception("Product not found");
          }
          foundEntry.setName(entry.name);
          foundEntry.setPrice(entry.price);
          return foundEntry;
    }

    @Override
    public Boolean delete(String key) throws Exception {
        var foundIndex = IntStream.range(0, this.inMemoryDB.size())
                .filter(i -> this.inMemoryDB.get(i).getCorrId().toString().equals(key))
                .findFirst().orElse(-1);
        if(foundIndex == -1){
            throw new Exception("Entry with id: " + key + "Does not exists");
        }
        this.inMemoryDB.remove(foundIndex);
        return true;
    }

    @Override
    public List<Product> search(String query) {
        return null;
    }
}
