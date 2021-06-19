package com.abhi.micronaut.service;


import com.abhi.micronaut.dao.ShoppingCartMapper;
import com.abhi.micronaut.model.ShoppingCart;
import com.abhi.micronaut.model.ShoppingModel;
import com.datastax.oss.driver.api.core.CqlSession;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Timestamp;
import java.util.Date;

@Singleton
public class StoreService{


@Inject
    CqlSession session;



    public ShoppingCart createUser(ShoppingModel model){

        ShoppingCart cart=new    ShoppingCart(model.getUserId(),model.getCount(),new Timestamp(new Date().getTime()));


        //ShoppingCartMapper inventoryMapper = new ShoppingCartMapperBuilder(session).build();

         return cart;
    }



}
