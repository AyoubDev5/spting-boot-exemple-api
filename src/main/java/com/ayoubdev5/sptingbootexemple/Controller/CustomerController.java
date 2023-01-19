package com.ayoubdev5.sptingbootexemple.Controller;

import com.ayoubdev5.sptingbootexemple.Entity.Customer;
import com.ayoubdev5.sptingbootexemple.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/allCustomers")
    public List<Customer> getCustomerByName(){
        return customerService.findAllCustomers();
    }

    @PostMapping("/createCustomer")
    public String addCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/customer/{name}")
    public List<Customer> getCustomerByName(@PathVariable("name") String name){
        return customerService.findCustomer(name);
    }

    @GetMapping("/customer/age")
    public List<Customer> getCustomerByAge(@RequestParam Integer minAge,@RequestParam Integer maxAge){
        return customerService.findCustomer(minAge,maxAge);
    }

    @GetMapping("/customer/search")
    public Page<Customer> getCustomerByAge(@RequestParam(required = false) String name,
                                           @RequestParam(required = false) Integer minAge,
                                           @RequestParam(required = false) Integer maxAge,
                                           @RequestParam(required = false) String city,
                                           @RequestParam(required = false) String country,
                                           @RequestParam(defaultValue = "0") Integer page,
                                           @RequestParam(defaultValue = "5") Integer size
    ){
        Pageable pageable = PageRequest.of(page,size);
        return customerService.search(name,minAge,maxAge,city,country,pageable);
    }

    @DeleteMapping("/deleteCustomer/{name}")
    public String deleteCustomer(@PathVariable("name") String name){
        return customerService.deleteCustomerByName(name);
    }

    /*@PutMapping ("/updateCustomer/{id}")
    public String updateCustomer(@PathVariable String id, @RequestBody Customer customer){
            Customer existCustomer = customerService.findById(id).orElse(null);
            existCustomer.setName(customer.getName());
            existCustomer.setEmail(customer.getEmail());
            existCustomer.setAge(customer.getAge());
            customerService.save(existCustomer);
            return "Customer has been updated successfully!";
    }*/
}
