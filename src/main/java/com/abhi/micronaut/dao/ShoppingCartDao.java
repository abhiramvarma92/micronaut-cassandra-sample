package com.abhi.micronaut.dao;


import com.abhi.micronaut.model.ShoppingCart;
import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.*;

@Dao
public interface ShoppingCartDao {
    @Select
    ShoppingCart findById(String userId);

    @Query("select * from store.shopping_cart")
    PagingIterable<ShoppingCart> findAll();


    @Insert
    void save(ShoppingCart product);

    @Delete
    void delete(ShoppingCart product);
}