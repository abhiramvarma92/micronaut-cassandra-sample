package com.abhi.micronaut.model;

import lombok.Data;

@Data
public class ShoppingModel
{

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private String userId;
    private int count;

}
