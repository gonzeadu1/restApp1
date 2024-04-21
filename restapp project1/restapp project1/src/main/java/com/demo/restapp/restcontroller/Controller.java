package com.demo.restapp.restcontroller;

import com.demo.restapp.Exceptions.CustomersNotFoundException;
import com.demo.restapp.Exceptions.ErrorMessage;
import com.demo.restapp.entity.Customers;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    }

    @GetMapping("/customers/{customerID}")
    public Customers getCustomerByID(@PathVariable int customerID){
        if(customerID >= customersList.size()){
            throw new CustomersNotFoundException("There no customer for id: " + customerID);
        }
        return customersList.get(customerID);
    }


    @GetMapping("/customers")
    public List<Customers> getCustomersList(){
        return customersList;
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleCustomerNotFoundException(CustomersNotFoundException e){
        ErrorMessage error = new ErrorMessage();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<ErrorMessage>(error, HttpStatus.NOT_FOUND);
    }
}
