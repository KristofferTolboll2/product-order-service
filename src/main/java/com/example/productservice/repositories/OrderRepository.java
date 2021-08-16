package com.example.productservice.repositories;

import com.example.productservice.models.Order;
import com.example.productservice.models.OrderDTO;
import com.example.productservice.models.Product;
import com.example.productservice.util.SortType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepository extends InMemoryDatastore<Order> implements BaseRepository<Order, String, OrderDTO> {

    @Override
    public Order create(OrderDTO entry) {
        var newOrder = new Order(entry);
        this.inMemoryDB.add(newOrder);
        return newOrder;
    }

    @Override
    public List<Order> getAll(SortType sort, Boolean isAscending) {
        switch (sort) {
            case CHRONOLOGICAL: {
                System.out.println(this.inMemoryDB);
                if (isAscending) this.inMemoryDB.sort(Comparator.comparing(Order::getCreatedAt));
                else this.inMemoryDB.sort(Comparator.comparing(Order::getCreatedAt).reversed());
                return this.inMemoryDB;
            }
            case AMOUNT: {
                if (isAscending) this.inMemoryDB.sort(Comparator.comparingInt(order -> order.getItems().size()));
                else this.inMemoryDB.sort((order, t1) -> order.getItems().size() + t1.getItems().size());
                return this.inMemoryDB;
            }
            default: {
                return this.inMemoryDB;
            }
        }
    }

    @Override
    public Order getByKey(String key) throws Exception {
        var foundEntry = this.inMemoryDB.stream().filter(entry -> entry.getOrderId().toString().equals(key)).collect(Collectors.toList()).get(0);
        if(foundEntry == null){
            //Custom expception should be implemented in the future to give more transparency to the end user of the API
            throw new Exception("Product not found");
        }
        return foundEntry;
    }


    /**
     * Update only items not price
     * @param key
     * @param entry
     * @return
     * @throws Exception
     */
    @Override
    public Order update(String key, OrderDTO entry) {
        return null;
    }

    /**
     * Only update price (as specified in project descrption this is done explicitly so the total order price is not automatically updated)
     * When a single product is updated.
     * @param orderId
     * @return
     */
    public float updatePrice(String orderId) throws Exception {
        var foundOrder = this.getByKey(orderId);
        float updatedPrice = 0;
        for (Product item: foundOrder.getItems()){
            updatedPrice += item.getPrice();
        }
        foundOrder.setPrice(updatedPrice);
        return foundOrder.getPrice();
    }

    @Override
    public Boolean delete(String key) { return null;
    }

    @Override
    public List<Order> search(String query) {
        return null;
    }
}
