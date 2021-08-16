package com.example.productservice.repositories;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class InMemoryDatastore<T> {
    public List<T> inMemoryDB;

    public InMemoryDatastore() {
        this.inMemoryDB = new ArrayList<>();
    }

}
