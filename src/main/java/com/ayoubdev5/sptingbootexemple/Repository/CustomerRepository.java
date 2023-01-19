package com.ayoubdev5.sptingbootexemple.Repository;

import com.ayoubdev5.sptingbootexemple.Entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Boolean deleteCustomerByName(String name);

    List<Customer> findCustomerByName(String name);

    @Query(value = "{'age' :  { '$gt' :  ?0, '$lt' :  ?1 }}", fields = "{'address' : 0}")
    List<Customer> findCustomerByAge(Integer minAge,Integer maxAge);
}
