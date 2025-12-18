package com.example.demo.controller;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRoleController {

    private final RoleService roleService;
    private final UserService userService;

    public UserRoleController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    
    @PostMapping("/roles/{roleName}")
    public Role createRole(@PathVariable String roleName) {
        return roleService.createRole(roleName);
    }

    
    @PostMapping("/users/{roleName}")
    public User createUser(@RequestBody User user,@PathVariable String roleName) {
        return userService.createUser(user, roleName);
    }
}
