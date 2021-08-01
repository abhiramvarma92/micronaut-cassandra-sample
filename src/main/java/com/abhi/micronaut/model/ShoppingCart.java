package com.abhi.micronaut.model;


import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {

    @PartitionKey String userid;
private Integer item_count;
private Instant last_update_timestamp;









}
