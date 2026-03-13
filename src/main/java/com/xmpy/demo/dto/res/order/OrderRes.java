package com.xmpy.demo.dto.res.order;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderRes {
    private long orderId;
    private String customerName;
    private String productSummary;
    private int totalPrice;
    private LocalDateTime orderDate;
    private String status;
}
