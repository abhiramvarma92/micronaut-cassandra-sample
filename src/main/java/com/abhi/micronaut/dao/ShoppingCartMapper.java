package com.abhi.micronaut.dao;


import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.DaoKeyspace;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

import javax.inject.Singleton;


    @Mapper
    public interface ShoppingCartMapper {
        @DaoFactory
        ShoppingCartDao storeDao(@DaoKeyspace CqlIdentifier keyspace);
    }
