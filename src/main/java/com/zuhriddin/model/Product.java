package com.zuhriddin.model;

import com.zuhriddin.enumeration.ProductState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private UUID id;
    private String name;
    private double price;
    private int amount;
    private String path;
    private String filePath;
    private ProductState productState;
}
