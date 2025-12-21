package com.example.demo.service.impl;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepreciationRuleServiceImpl implements DepreciationRuleService {

    private final DepreciationRuleRepository ruleRepository;

    public DepreciationRuleServiceImpl(DepreciationRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public DepreciationRule createRule(DepreciationRule rule) {

        if (rule == null) {
            throw new IllegalArgumentException("Depreciation rule must not be null");
        }

        if (rule.getUsefulLifeYears() == null || rule.getUsefulLifeYears() <= 0) {
            throw new IllegalArgumentException("Useful life must be greater than 0");
        }

        if (rule.getSalvageValue() == null || rule.getSalvageValue() < 0) {
            throw new IllegalArgumentException("Salvage value cannot be negative");
        }

        if (rule.getMethod() == null ||
                (!"STRAIGHT_LINE".equals(rule.getMethod())
                        && !"DECLINING_BALANCE".equals(rule.getMethod()))) {
            throw new IllegalArgumentException("Invalid depreciation method");
        }

        rule.setCreatedAt(LocalDateTime.now());
        return ruleRepository.save(rule);
    }

    @Override
    public List<DepreciationRule> getAllRules() {
        return ruleRepository.findAll();
    }
}
