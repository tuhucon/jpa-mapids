package com.example.jpamapids.controller;

import com.example.jpamapids.TestRepository;
import com.example.jpamapids.entity.Test;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
public class AuditController {

    private final TestRepository testRepository;

    private final MeterRegistry meterRegistry;

    @GetMapping("/audit")
    public void audit(@RequestParam Long id) {
        Test test = new Test();
        test.setName("audit");
        testRepository.save(test);
        Utils.printHikariConnectionMetric(meterRegistry);

        Test testId = testRepository.findById(id).get();
        testId.setName("audit " + ThreadLocalRandom.current().nextInt(0, 10_000));
        Utils.printHikariConnectionMetric(meterRegistry);

        testRepository.save(testId);
        Utils.printHikariConnectionMetric(meterRegistry);
    }

}
