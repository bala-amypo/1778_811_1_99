package com.example.demo.controller;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.DepreciationRuleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class DepreciationRuleController {

    private final DepreciationRuleRepository ruleRepository;

    public DepreciationRuleController(DepreciationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @PostMapping
    public DepreciationRule create(@RequestBody DepreciationRule rule) {
        if (rule.getRuleName() == null || rule.getMethod() == null) {
            throw new BadRequestException("Invalid rule data");
        }
        return ruleRepository.save(rule);
    }

    @GetMapping
    public List<DepreciationRule> getAll() {
        return ruleRepository.findAll();
    }
}
