package com.ayoubdev5.sptingbootexemple.Service;

import com.ayoubdev5.sptingbootexemple.Entity.Customer;
import com.ayoubdev5.sptingbootexemple.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String saveCustomer(Customer customer) {
        return customerRepository.save(customer).getName();
    }

    @Override
    public List<Customer> findCustomer(String name) {
        return customerRepository.findCustomerByName(name);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public String deleteCustomerByName(String name) {
        boolean customerDelete = customerRepository.deleteCustomerByName(name);
        return customerDelete ? "Customer has been deleted successfully" : "Customer not Deleted !";
    }

    @Override
    public List<Customer> findCustomer(Integer minAge, Integer maxAge) {
        return customerRepository.findCustomerByAge(minAge, maxAge);
    }

    @Override
    public Page<Customer> search(String name, Integer minAge, Integer maxAge, String city, String country, Pageable pageable) {
        Query query = new Query().with(pageable);
        List<Criteria> criteria = new ArrayList<>();

        if(name!=null && !name.isEmpty()){
            criteria.add(Criteria.where("name").regex(name,"i"));
        }
        if(minAge!=null && maxAge!=null){
            criteria.add(Criteria.where("age").gte(minAge).lte(maxAge));
        }
        if(city!=null && !city.isEmpty()){
            criteria.add(Criteria.where("address.city").is(city));
        }
        if(country!=null && !country.isEmpty()){
            criteria.add(Criteria.where("address.country").is(country));
        }
        if(!criteria.isEmpty()){
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
        }
        Page<Customer> customers = PageableExecutionUtils.getPage(
                mongoTemplate.find(query, Customer.class),
                pageable,
                ()->mongoTemplate.count(query.skip(0).limit(0),Customer.class)
        );
        return customers;
    }

}
