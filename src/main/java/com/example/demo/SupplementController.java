package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/supplements")
public class SupplementController {

    private final DNIAnalysisService dniAnalysisService;

    public SupplementController(DNIAnalysisService dniAnalysisService) {
        this.dniAnalysisService = dniAnalysisService;
    }

    @PostMapping("/analyze")
    public List<InteractionResult> analyze(@RequestBody List<String> supplements) {
        return dniAnalysisService.analyze(supplements);
    }
}