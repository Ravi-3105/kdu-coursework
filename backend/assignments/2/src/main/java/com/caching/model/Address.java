package com.caching.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Automatic getter setter at runtime lombok library
@AllArgsConstructor // for constructor during compile time
public class Address {
    String address;
}
