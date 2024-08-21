package com.zuhriddin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private UUID id;
    private String name;
    private int age;
    private String phoneNumber;
    private String address;
}
