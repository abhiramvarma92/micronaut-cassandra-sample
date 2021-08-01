package com.abhi.micronaut.config;


import com.abhi.micronaut.dao.ShoppingCartMapperBuilder;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.type.codec.TypeCodecs;
import io.micronaut.context.annotation.*;
import io.micronaut.runtime.http.scope.RequestScope;

import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Config factory for creation of cassandra config
 */
@Factory
@Requires(beans = {CassandraProperties.class})
public class CassandraConfig {


    private final List<CqlSession>  sessionList= Collections.synchronizedList(new CopyOnWriteArrayList<>());
    private int sessionCount=0;

    /**
     * Created this as  singleton as this is heavy object
     * @param properties
     * @return
     */
    @Singleton
    @Replaces(bean = CqlSessionBuilder.class)
    public CqlSessionBuilder builder(@Singleton CassandraProperties properties){
     return new CqlSessionBuilder().addContactPoint(new InetSocketAddress(properties.getContactPoint(),properties.getPort())).withLocalDatacenter(properties.getDatacenter());


    }

    /** Created Cassandra session with  session limit  taken from Config
     *
     *
     * @param builder
     * @param properties
     * @return
     */
    @Bean(preDestroy = "close")
    @Requires(beans = {CqlSessionBuilder.class})
    public CqlSession cqlSession( CqlSessionBuilder builder,@Singleton CassandraProperties properties){

        if(sessionList.size()<=properties.getSessionLimit()) {
            CqlSession session = builder.withKeyspace(properties.getKeyspace()).addTypeCodecs(TypeCodecs.TIMESTAMP)
                    .build();
            sessionList.add(session);
            return session;
        }
        else {
            if(sessionCount==properties.getSessionLimit())
             sessionCount=0;
            else
                sessionCount++;
            return sessionList.get(sessionCount);
        }

    }


    /**
     * Builder is created with request scope but we can also create builder with prototype if we want to inject
     * different builder instance for different injection points
     *
     * @param cqlSession
     * @return
     */
    @RequestScope
    @Requires(beans = {CqlSession.class})
    public ShoppingCartMapperBuilder builder(CqlSession cqlSession){
     return new ShoppingCartMapperBuilder(cqlSession);
    }


   @PreDestroy
    public void close(){
      this.sessionList.stream().forEach(stre->stre.close());
    }

}
