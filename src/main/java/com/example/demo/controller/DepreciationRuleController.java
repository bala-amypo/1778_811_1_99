package com.example.demo.controller;
import jakarta.validation.Valid;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class DepreciationRuleController {

    @Autowired
    private DepreciationRuleRepository ruleRepository;

    @PostMapping
    public DepreciationRule create(@Valid @RequestBody DepreciationRule rule) {
        return ruleRepository.save(rule);
    }

    @GetMapping
    public List<DepreciationRule> getAll() {
        return ruleRepository.findAll();
    }
}
