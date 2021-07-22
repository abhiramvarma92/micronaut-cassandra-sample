package com.abhi.micronaut.service;


import com.abhi.micronaut.config.CassandraConfig;
import com.abhi.micronaut.dao.ShoppingCartDao;
import com.abhi.micronaut.dao.ShoppingCartMapper;
import com.abhi.micronaut.dao.ShoppingCartMapperBuilder;
import com.abhi.micronaut.model.ShoppingCart;
import com.abhi.micronaut.model.ShoppingModel;
import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import io.micronaut.context.annotation.Requires;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;
import java.util.List;

@Singleton
@Requires(beans = {CassandraConfig.class,CqlSession.class})
public class StoreService {


    @Inject
    CqlSession session;


    public ShoppingCart createUser(ShoppingModel model) {


        ShoppingCart cart = new ShoppingCart(model.getUserId(), model.getCount(), Instant.now());

        ShoppingCartMapper storeMapper = new ShoppingCartMapperBuilder(session).build();

        ShoppingCartDao shoppingDao = storeMapper.storeDao(CqlIdentifier.fromCql("store"));

        shoppingDao.save(cart);

        return cart;
    }


    public List<ShoppingCart> getUsers(){

        
        ShoppingCartMapper storeMapper = new ShoppingCartMapperBuilder(session).build();

        ShoppingCartDao shoppingDao = storeMapper.storeDao(CqlIdentifier.fromCql("store"));
        List<ShoppingCart> list=shoppingDao.findAll().all();

        return list;


    }

    public String deleteUser(ShoppingModel model) {
        ShoppingCart cart = new ShoppingCart(model.getUserId(), model.getCount(), Instant.now());
        ShoppingCartMapper storeMapper = new ShoppingCartMapperBuilder(session).build();

        ShoppingCartDao shoppingDao = storeMapper.storeDao(CqlIdentifier.fromCql("store"));

        shoppingDao.delete(cart);
        return String.format("Deleted User with Id : %s",cart.getUserid());
    }
}
