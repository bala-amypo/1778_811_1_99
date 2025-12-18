package com.example.demo.service;
import com.example.demo.entity.User;
public interface UserService{
    Role createUser(User user,String rolename);
}