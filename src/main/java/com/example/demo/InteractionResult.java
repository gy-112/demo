package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InteractionResult {
    private String supplement1;
    private String supplement2;
    private String riskLevel;   // HIGH, MEDIUM, LOW
    private String description;
}