package com.ayoubdev5.sptingbootexemple.Entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String address1;
    private String address2;
    private String country;
    private String city;
}
