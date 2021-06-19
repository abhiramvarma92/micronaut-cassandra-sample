package com.abhi.micronaut.controller;


import com.abhi.micronaut.model.ShoppingCart;
import com.abhi.micronaut.model.ShoppingModel;
import com.abhi.micronaut.service.StoreService;
import com.datastax.oss.driver.api.core.CqlSession;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

@Controller
public class StoreController {


    @Inject
    StoreService service;



    @Post("/createStoreUser")
    public Single<HttpResponse<ShoppingCart>> save(@Body Single<ShoppingModel> model) {
        return model.map(p -> {
            return HttpResponse.created(service.createUser(p));
                }
        );
    }


}
