package com.demo.service.impl;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.repository.UserRepository;
import org.framework.stereotype.Service;
import java.util.Set;
@Service

public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepositor
    public UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository){
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
    }
    @Override
    public createUser(User user,String rolename){
        Role role=roleRepository.findByName(rolename).orElseThrow(()->new RunTimeException("Role not found: "+rolename));
        user.setRoles(Set.of(role));
        return userRepository.save(user);
    }
}
