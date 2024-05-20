package com.example.jpamapids.controller;

import com.example.jpamapids.entity.Author;
import com.example.jpamapids.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
public class BatchController {

    private final AuthorRepository authorRepository;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    @GetMapping("/batchInsert")
    public void batchInsert(@RequestParam Integer number) {
        List<Author> authors = new ArrayList<>(batchSize);
        for (int i = 0; i < number; i++) {
            Author a = new Author();
            a.setId(i + 1L);
            a.setAge(ThreadLocalRandom.current().nextInt(10, 110));
            a.setName("tu hu con " + a.getAge());
            authors.add(a);
            if (authors.size() % batchSize == 0) {
                batchTransactionInsert(authors);
            }
        }
        if (authors.size() > 0) {
            batchTransactionInsert(authors);
        }
    }

    private int count = 0;
    void batchTransactionInsert(List<Author> authors) {
        count++;
        authorRepository.saveAll(authors);
        authors.clear();
        System.out.println("count = " + count);
    }
}
