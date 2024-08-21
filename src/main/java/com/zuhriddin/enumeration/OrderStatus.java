package com.zuhriddin.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {

    IN_BUCKET,
    PENDING ,
    PROCESSING ,
    SHIPPED ,
    DELIVERED,
    CANCELLED


}
