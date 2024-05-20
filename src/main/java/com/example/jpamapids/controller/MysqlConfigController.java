package com.example.jpamapids.controller;

import com.example.jpamapids.TestRepository;
import com.example.jpamapids.entity.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
public class MysqlConfigController {

    private final TestRepository testRepository;

    @GetMapping("/confirmConfig")
    @Transactional
    public void confirmConfig() {
        Test test = testRepository.findById(1L).get();
        test.setName("tu hu con: " + ThreadLocalRandom.current().nextInt(0, 100));

        Test tes2 = testRepository.findById(2L).get();
        test.setName("chich choe: " + ThreadLocalRandom.current().nextInt(0, 100));
    }
}
