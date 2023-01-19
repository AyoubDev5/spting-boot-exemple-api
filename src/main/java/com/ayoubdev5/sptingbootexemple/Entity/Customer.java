package com.ayoubdev5.sptingbootexemple.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
@Builder
@Document(collection = "customer")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {
    @Id
    private String id;
    private String name;
    private Integer age;
    private List<String> hobbies;
    private Address address;
}
