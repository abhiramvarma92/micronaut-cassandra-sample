package com.abhi.micronaut.config;


import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import io.micronaut.context.annotation.*;
import jnr.ffi.types.clock_t;

import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import java.net.InetSocketAddress;
import java.util.List;

@EachProperty(value = "cassandra.default")
@ConfigurationProperties("cassandra.default")
@Factory
public class CassandraConfig {

    private String clusterName;
    private String contactPoint;
    private Integer port;
    private int maxSchemaAgreementWaitSeconds;
    private boolean ssl;

    private List<CqlSession>  sessionList= null;

    @Singleton
    public CqlSessionBuilder builder(){
     return new CqlSessionBuilder().addContactPoint(new InetSocketAddress(contactPoint,port))
             .withLocalDatacenter(clusterName);

    }

    @Bean(preDestroy = "close")
    @Requires(classes = {CqlSessionBuilder.class})
    public CqlSession buildSession(CqlSessionBuilder builder){


        CqlSession session= builder
                .build();
        sessionList.add(session);
        return session;
    }

   @PreDestroy
    public void close(){

      this.sessionList.stream().forEach(stre->stre.close());

    }

}
