package com.demo.restapp.restcontroller;

import com.demo.restapp.Exceptions.CustomerExists;
import com.demo.restapp.Exceptions.CustomersNotFoundException;
import com.demo.restapp.entity.Customers;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.Customizer;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    List<Customers> customersList = new ArrayList<>();


    @PostConstruct
    public void loadCustomers(){
        customersList.add(new Customers(1, "Fidelis", "fidelis@email.com"));
        customersList.add(new Customers(2, "Gideon", "gideon@emailcom"));
        customersList.add(new Customers(3, "Jennifer", "gideon@emailcom"));
    }

    @GetMapping("/customers/{customerID}")
    public Customers getCustomerByID(@PathVariable int customerID){
        int index = customerID -1;
        if(index <0 || index >= customersList.size()){
            throw new CustomersNotFoundException("There no customer for id: " + customerID);
        }
        return customersList.get(index);
    }


    @GetMapping("/customers")
    public List<Customers> getCustomersList(){
        System.out.println("I am testing");
        return customersList;
    }

    @PostMapping("/customers")
    public ResponseEntity<String> addCustomer(@RequestBody Customers customer){
        for(Customers allCustomers : customersList) {
            if (allCustomers.getId() == (customer.getId())) {
                throw new CustomerExists("There is already a customer with this id: " + customer.getId() + ". Try again with another.");

            }
        }
        customersList.add(customer);
        return ResponseEntity.ok("Customer added!");
    }
    //update Customer
//    @Property(propertyName = "ninesFlag")
    @PutMapping("/customers/{customerID}")
    public ResponseEntity<String> updateCustomer(@PathVariable int customerID, @RequestBody Customers customer){
        if(customerID >= customersList.size()){
            throw new CustomersNotFoundException("There is no customer with id: " + customerID);
        }

        else{
            Customers currentCustomer = customersList.get(customerID);
            currentCustomer.setEmail(customer.getEmail());
            currentCustomer.setName(customer.getName());

            return ResponseEntity.ok("Customer updated!");
        }

    }
    @DeleteMapping("/customers/{customerID}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int customerID){
        int indexNumber = customerID - 1;
        Customers currentCustomer = customersList.get(indexNumber);
        if(customersList.contains(currentCustomer)){
            customersList.remove(currentCustomer);
            return ResponseEntity.ok("Customer deleted");
        }
        else{
            throw new CustomersNotFoundException("There is no customer for id: " + customerID);
        }

    }


}
