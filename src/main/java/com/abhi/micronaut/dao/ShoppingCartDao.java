package com.abhi.micronaut.dao;


import com.abhi.micronaut.model.ShoppingCart;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;

@Dao
public interface ShoppingCartDao {
    @Select
    ShoppingCart findById(String userId);

    @Insert
    void save(ShoppingCart product);

    @Delete
    void delete(ShoppingCart product);
}