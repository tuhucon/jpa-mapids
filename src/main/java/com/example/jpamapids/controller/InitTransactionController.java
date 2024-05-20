package com.example.jpamapids.controller;

import com.example.jpamapids.AbcService;
import com.zaxxer.hikari.HikariDataSource;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InitTransactionController {

    private final AbcService abcService;

    private final MeterRegistry meterRegistry;

    @GetMapping("/testInit")
    public void testInit() throws InterruptedException {
        Utils.printHikariConnectionMetric(meterRegistry);
        abcService.test();
        Utils.printHikariConnectionMetric(meterRegistry);
    }
}
