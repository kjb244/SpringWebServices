package main.java.com.spring.controller;

import main.java.com.spring.model.User;
import main.java.com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value="/rest")
public class HelloWorldService implements IRestController {

    @Autowired
    private UserService userService;

    //rest/customers
    @RequestMapping(value="/customers", method = RequestMethod.GET)
    public ResponseEntity getCustomer(){
        return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);
    }

    //rest/customersByAge/23
    @RequestMapping(value="/customersByAge/{age}", method = RequestMethod.GET)
    public ResponseEntity getCustomerByAge(@PathVariable String age){
        if(age.matches("^\\d+$")){
            return new ResponseEntity(userService.getByAge(Integer.parseInt(age)), HttpStatus.OK);

        }

        List<User> arr = new ArrayList<User>();
        return new ResponseEntity(arr, HttpStatus.OK);
    }

    //rest/removeCustomer
    @RequestMapping(value="/removeCustomer", method = RequestMethod.POST)
    public ResponseEntity removeCustomer(@Valid @RequestBody User user, Errors errors) {
        List<String> errorsArr = processErrors(errors);
        if (null != errorsArr) return new ResponseEntity(errorsArr, HttpStatus.OK);
        Map<String, Boolean> status = new HashMap<String, Boolean>();
        if(user instanceof User){
            status.put("status", userService.delete(user));
        }
        else{
            status.put("status", false);
        }
        return new ResponseEntity(status, HttpStatus.OK);

    }

    public List<String> processErrors(Errors errors){
        if (null != errors && errors.hasErrors()){
            return errors.getAllErrors()
                    .stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList());
        }
        return null;
    }
}
