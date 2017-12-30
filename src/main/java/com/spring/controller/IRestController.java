package main.java.com.spring.controller;

import main.java.com.spring.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import java.util.List;

public interface IRestController {

    ResponseEntity getCustomer();
    ResponseEntity getCustomerByAge(String age);
    ResponseEntity removeCustomer(User user, Errors errors);
    List<String> processErrors(Errors errors);
}
