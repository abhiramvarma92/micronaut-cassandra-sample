package com.abhi.micronaut.model;


import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

import java.sql.Timestamp;


@Entity
public class ShoppingCart {

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    public Timestamp getLast_update_timestamp() {
        return last_update_timestamp;
    }

    public void setLast_update_timestamp(Timestamp last_update_timestamp) {
        this.last_update_timestamp = last_update_timestamp;
    }

    public ShoppingCart(String userid, int item_count, Timestamp last_update_timestamp) {
        this.userid = userid;
        this.item_count = item_count;
        this.last_update_timestamp = last_update_timestamp;
    }

    @PartitionKey String userid;
private int item_count;
private Timestamp last_update_timestamp;









}
