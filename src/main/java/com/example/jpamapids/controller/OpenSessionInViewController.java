package com.example.jpamapids.controller;

import com.example.jpamapids.TestRepository;
import com.example.jpamapids.TransactionService;
import com.example.jpamapids.entity.Test;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
public class OpenSessionInViewController {

    private final TransactionService transactionService;

    private final TestRepository testRepository;

    private final MeterRegistry meterRegistry;

    @GetMapping("/checkOSIV")
    public Test checkOSIV() throws InterruptedException {
        System.out.println("start controller method");
        Utils.printHikariConnectionMetric(meterRegistry);
        System.out.println("sleep done, call transaction service to get Test");
        Test test = transactionService.getTest(1L);
        System.out.println("done select test object, start sleep");
        Utils.printHikariConnectionMetric(meterRegistry);

        System.out.println("start update test in controller");
        transactionService.updateTest(1L, "name: " + ThreadLocalRandom.current().nextInt(0, 100));
        System.out.println("done update test in controller");
        Utils.printHikariConnectionMetric(meterRegistry);
        System.out.println(test);
//        System.out.println(test.getTestChild());

        System.out.println("done controller method, return test to view");
        return test;
    }

    @GetMapping("selectUpdateOSIV")
    public void selectUpdateOSIV() throws InterruptedException {
        Test test = testRepository.findById(1L).get();
        test.setName("new name: " + ThreadLocalRandom.current().nextInt(0, 100));
        testRepository.save(test);
    }
}
