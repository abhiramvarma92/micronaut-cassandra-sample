package com.abhi.micronaut.service;


import com.abhi.micronaut.dao.ShoppingCartDao;
import com.abhi.micronaut.dao.ShoppingCartMapper;
import com.abhi.micronaut.dao.ShoppingCartMapperBuilder;
import com.abhi.micronaut.model.ShoppingCart;
import com.abhi.micronaut.model.ShoppingModel;
import com.datastax.oss.driver.api.core.CqlIdentifier;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;
import java.util.List;

/**
 * This service is used for working with Store and creation,deletion and other functionalities
 *
 */
@Singleton
public class StoreService {

    /**
     * Builder here is created for request scope from Config and you can also change to prototype based on your
     * requirements .If you use prototype different bean is inject for different instance
     */
     @Inject
     public ShoppingCartMapperBuilder builder;


    /**
     * This method is used for sample User creation
     * @param model
     * @return
     */
    public ShoppingCart createUser(ShoppingModel model) {
        ShoppingCart cart = new ShoppingCart(model.getUserId(), model.getCount(), Instant.now());
        ShoppingCartMapper storeMapper = builder.build();
        ShoppingCartDao shoppingDao = storeMapper.storeDao(CqlIdentifier.fromCql("store"));
        shoppingDao.save(cart);
        return cart;
    }

    /**
     * This method is used for getting all the users
     * @return
     */
    public List<ShoppingCart> getUsers(){

        ShoppingCartMapper storeMapper = builder.build();
        ShoppingCartDao shoppingDao = storeMapper.storeDao(CqlIdentifier.fromCql("store"));
        List<ShoppingCart> list=shoppingDao.findAll().all();
        return list;
    }

    /**
     * This method is used for deletion of user
     * @param model
     * @return
     */
    public String deleteUser(String id) {
        ShoppingCart cart = new ShoppingCart(id, null, Instant.now());
        ShoppingCartMapper storeMapper = builder.build();
        ShoppingCartDao shoppingDao = storeMapper.storeDao(CqlIdentifier.fromCql("store"));
        shoppingDao.delete(cart);
        return String.format("Deleted User with Id : %s",cart.getUserid());
    }
}
