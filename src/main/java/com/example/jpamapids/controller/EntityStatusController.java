package com.example.jpamapids.controller;

import com.example.jpamapids.AuthorRepository;
import com.example.jpamapids.entity.Author;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
public class EntityStatusController {

    private final AuthorRepository authorRepository;

    private final MeterRegistry meterRegistry;

    @GetMapping("/entityStatus")
    public void entityStatus() throws InterruptedException {
        Author author = authorRepository.findById(1L).get();
        Thread.sleep(5_000L);
        Author author2 = authorRepository.findById(2L).get();
        Thread.sleep(5_000L);
        author.setAge(ThreadLocalRandom.current().nextInt(0,130));
        authorRepository.save(author);
        Thread.sleep(5_000);
        author2.setAge(ThreadLocalRandom.current().nextInt(0, 150));
        authorRepository.save(author2);
        Thread.sleep(5_000L);
    }

    @GetMapping("/delayQuery")
    @Transactional
    public void delayQuery() throws InterruptedException {
        Utils.printHikariConnectionMetric(meterRegistry);
        Thread.sleep(40_000L);
        System.out.println("*********done sleep, start query");
        Utils.printHikariConnectionMetric(meterRegistry);
        authorRepository.findById(1L).get();
        Utils.printHikariConnectionMetric(meterRegistry);
    }
}
