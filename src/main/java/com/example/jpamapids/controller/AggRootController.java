package com.example.jpamapids.controller;

import com.example.jpamapids.AuthorRepository;
import com.example.jpamapids.entity.Author;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AggRootController {

    private final AuthorRepository authorRepository;

    private final MeterRegistry meterRegistry;

    @GetMapping("/aggRoot")
    @Transactional
    public void aggRoot() {
        System.out.println("*********agg Root running thread: " + Thread.currentThread().getName());
        Utils.printHikariConnectionMetric(meterRegistry);
        Author author = new Author();
        author.setAge(30);
        author.setName("tac ke hoa");
        authorRepository.save(author);
        System.out.println("*********agg Root end " + Thread.currentThread().getName());
        Utils.printHikariConnectionMetric(meterRegistry);
    }
}
