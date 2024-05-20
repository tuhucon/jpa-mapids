package com.example.jpamapids.controller;

import com.example.jpamapids.TestRepository;
import com.example.jpamapids.entity.Test;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@AllArgsConstructor
public class InterfaceTransactionController {

    private final TestRepository testRepository;

    @GetMapping("/updateTestName")
    @Transactional
    public void updateTestName() {
        Test test = testRepository.findById(1L).get();
        testRepository.updateNameJpql(1L, "test " + ThreadLocalRandom.current().nextInt(0, 100));
//        testRepository.updateNameRaw(1L, "test " + ThreadLocalRandom.current().nextInt(0, 100));
        test = testRepository.findById(1L).get();
    }


}
