package com.example.jpamapids.controller;

import com.example.jpamapids.ProjectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final ProjectionService projectionService;

    @GetMapping("/transaction")
    public void transaction() {
        projectionService.outerTransaction();
    }
}
