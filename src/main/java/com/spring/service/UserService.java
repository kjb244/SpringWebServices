package main.java.com.spring.service;

import main.java.com.spring.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private List<User> users;

    public UserService(){
        users = new ArrayList<User>();
        users.add(new User("Kevin", "Johnson", 23));
        users.add(new User("Sarah", "Hitall", 12));
        users.add(new User("Marley", "Szichis", 44));
    }

    public List<User> getAllUsers(){
        return users;
    }

    public List<User> getByAge(int age) {
        return users.stream().filter(e -> e.getAge() == age).collect(Collectors.toList());
    }

    public boolean delete(User user){
        int idx = users.indexOf(user);
        if (idx > -1){
            users.remove(idx);
            return true;
        }
        return false;
    }



}
