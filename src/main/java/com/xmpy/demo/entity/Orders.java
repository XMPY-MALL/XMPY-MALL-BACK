package com.xmpy.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Orders {
    private long ordersId;
    private long userId;
    private String status;
    private long totalPrice;
    private String address;
    private LocalDateTime creatdAt;
    private LocalDateTime updateAt;

}
