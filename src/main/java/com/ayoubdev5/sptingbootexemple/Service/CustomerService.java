package com.ayoubdev5.sptingbootexemple.Service;

import com.ayoubdev5.sptingbootexemple.Entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

public interface CustomerService {

     String saveCustomer(Customer customer);

     List<Customer> findCustomer(String name);

     List<Customer> findAllCustomers();

     String deleteCustomerByName(String name);

     List<Customer> findCustomer(Integer minAge, Integer maxAge);

     Page<Customer> search(String name, Integer minAge, Integer maxAge, String city, String country, Pageable pageable);

}
