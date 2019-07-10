package com.example.kafka.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author 彬
 * @Date 2019/7/10
 */
@Data
public class OrderEntity implements Serializable {

    private Integer userId;

    private BigDecimal payment;

    private Date createTime;
}
