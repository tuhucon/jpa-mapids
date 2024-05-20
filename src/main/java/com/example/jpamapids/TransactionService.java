package com.example.jpamapids;

import com.example.jpamapids.controller.Utils;
import com.example.jpamapids.entity.Test;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TestRepository testRepository;
    private final MeterRegistry meterRegistry;

    @Transactional
    public Test getTest(Long id) throws InterruptedException {
        System.out.println("start test in transaction service, sleep 30s");
        Utils.printHikariConnectionMetric(meterRegistry);
        Thread.sleep(3_000L);
        Test test = testRepository.findById(1L).get();
        System.out.println("done select test, sleep 30s");
        Utils.printHikariConnectionMetric(meterRegistry);
        Thread.sleep(3_000L);
        System.out.println("done test in transaction service, return test object");
        return test;
    }

    @Transactional
    public void updateTest(Long id, String name) {
        System.out.println("start update test method in transaction service");
        Utils.printHikariConnectionMetric(meterRegistry);
        testRepository.updateNameJpql(id, name);
        System.out.println("done update in transaction service");
        Utils.printHikariConnectionMetric(meterRegistry);
        System.out.println("done update test method");
    }
}
