package com.abhi.micronaut.config;


import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties(value = "cassandra.default")
public class CassandraProperties {

    private String clusterName;
    private String contactPoint;
    private Integer port;
    private int maxSchemaAgreementWaitSeconds;
    private boolean ssl;
    private String datacenter;
    private String keyspace;
    private int sessionLimit;
}
