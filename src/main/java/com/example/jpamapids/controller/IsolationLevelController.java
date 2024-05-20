package com.example.jpamapids.controller;

import com.example.jpamapids.AsyncService;
import com.example.jpamapids.TestRepository;
import com.example.jpamapids.entity.Test;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
public class IsolationLevelController {

    //mysql db 's isolation level = read_committed
    private final TestRepository testRepository;

    private final MeterRegistry meterRegistry;

    private final AsyncService asyncService;

    @GetMapping("/checkIsolation")
    @Transactional
    public void checkIsolation() throws ExecutionException, InterruptedException {
        Test test = testRepository.findById(1L).get();
        System.out.println(test);

        asyncService.updateTest(testRepository, 1L).get();
        System.out.println("update name of entity is done, reload entity to check name - see SQL query too");
        Utils.printHikariConnectionMetric(meterRegistry);

        System.out.println(testRepository.findByIdJpal(1L));
        System.out.println(testRepository.findByIdRaw(1L));

        System.out.println(testRepository.findNameByIdJapl(1L));
        System.out.println(testRepository.findNameByIdRaw(1L));
    }


}
