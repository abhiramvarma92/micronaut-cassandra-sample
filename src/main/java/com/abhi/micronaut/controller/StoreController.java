package com.abhi.micronaut.controller;


import com.abhi.micronaut.model.ShoppingCart;
import com.abhi.micronaut.model.ShoppingModel;
import com.abhi.micronaut.service.StoreService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;

import javax.inject.Inject;
import java.util.List;

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

    @Get("/showUsers")
    public Single<HttpResponse<List<ShoppingCart>>> showAllUsers() {
        return Single.fromCallable(()->HttpResponse.created(service.getUsers()));
    }

    @Post("/deleteUser")
    public Single<HttpResponse<String>> deleteUser(@Body Single<ShoppingModel> model) {
        return model.map(p -> {
                    return HttpResponse.created(service.deleteUser(p));
                }
        );
    }


}
