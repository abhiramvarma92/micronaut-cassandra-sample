package com.abhi.micronaut.config;


import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.type.codec.TypeCodecs;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;

import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Factory
@Requires(beans = {CassandraProperties.class})
public class CassandraConfig {





    private final List<CqlSession>  sessionList= Collections.synchronizedList(new CopyOnWriteArrayList<>());

    @Singleton
    @Replaces(bean = CqlSessionBuilder.class)
    public CqlSessionBuilder builder(@Singleton CassandraProperties properties){
     return new CqlSessionBuilder().addContactPoint(new InetSocketAddress(properties.getContactPoint(),properties.getPort())).withLocalDatacenter(properties.getDatacenter());


    }

    @Bean(preDestroy = "close")
    @Requires(beans = {CqlSessionBuilder.class})
    public CqlSession buildSession( CqlSessionBuilder builder,@Singleton CassandraProperties properties){

        CqlSession session= builder.withKeyspace(properties.getKeyspace()).addTypeCodecs(TypeCodecs.TIMESTAMP)
                .build();
        sessionList.add(session);
        return session;
    }


   @PreDestroy
    public void close(){

      this.sessionList.stream().forEach(stre->stre.close());

    }

}
