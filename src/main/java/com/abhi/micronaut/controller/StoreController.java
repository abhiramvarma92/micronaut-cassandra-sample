package com.abhi.micronaut.controller;


import com.abhi.micronaut.model.ShoppingCart;
import com.abhi.micronaut.model.ShoppingModel;
import com.abhi.micronaut.service.StoreService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.reactivex.Single;

import javax.inject.Inject;
import java.util.List;

@Controller
public class StoreController {


    @Inject
    StoreService service;



    /**
     *Used for creation of store USer
     * @param model
     * @return
     */

    @Post("/createStoreUser")
    public Single<HttpResponse<ShoppingCart>> save(@Body Single<ShoppingModel> model) {
        return model.map(p -> {
            return HttpResponse.created(service.createUser(p));
                }
        );
    }

    /**
     *Used to get all Users
     * @return
     */
    @Get("/showUsers")
    public Single<HttpResponse<List<ShoppingCart>>> showAllUsers() {
        return Single.fromCallable(()->HttpResponse.created(service.getUsers()));
    }

    /**
     *used to delete User
     * @param id
     * @return
     */
    @Delete("/deleteUser/{userId}")
    public Single<HttpResponse<String>> deleteUser(@PathVariable("userId") String id) {
        return Single.fromCallable(()->HttpResponse.ok(service.deleteUser(id)));
    }


}
