package com.example.productservice.repositories;

import com.example.productservice.util.SortType;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T, K, D> {
    public T create(D entry);
    public List<T> getAll(SortType sort, Boolean isAscending);
    public T getByKey(K key) throws Exception;
    public T update(K key, D entry) throws Exception;
    public Boolean delete(K key) throws Exception;
    public List<T> search(String query);
}
