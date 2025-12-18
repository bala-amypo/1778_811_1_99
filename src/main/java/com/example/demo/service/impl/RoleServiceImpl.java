package com.demo.service.impl;
imnport com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.framework.stereotype.Service;
@Service
@Column(unique=true)
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository=roleRepository;
    }
    @Override
    public createRole(String rolename){
        return roleRepository.findByName(rolename).orElse(()=>roleRepository.save(new Role(roleName)));
    }
}
