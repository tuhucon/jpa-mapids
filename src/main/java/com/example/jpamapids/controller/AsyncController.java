package com.example.jpamapids.controller;

import com.example.jpamapids.AsyncService;
import com.example.jpamapids.AuthorRepository;
import com.example.jpamapids.BookRepository;
import com.example.jpamapids.entity.Author;
import com.example.jpamapids.entity.Book;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class AsyncController {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    private final MeterRegistry meterRegistry;

    private final AsyncService asyncService;

    @GetMapping("/asyncTest")
    public void asyncTest() throws ExecutionException, InterruptedException {
        Utils.printHikariConnectionMetric(meterRegistry);
        var bookFuture = asyncService.getBookById(bookRepository, 1L);
        var authorFuture = asyncService.getAuthorById(authorRepository, 1L);

        Book book = bookFuture.get().get();
        Author author = authorFuture.get().get();

        System.out.println("********asyncTest query data done");
        Utils.printHikariConnectionMetric(meterRegistry);

        System.out.println("****doing heavy task in 40s");
        try {
            Thread.sleep(40_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("*******end heavy task, save data");
        Utils.printHikariConnectionMetric(meterRegistry);
        saveData(author, book);
        System.out.println("**********save done");
        Utils.printHikariConnectionMetric(meterRegistry);
    }

    @Transactional
    void saveData(Author author, Book book) {
        System.out.println("save data in: " + Thread.currentThread().getName());
        Utils.printHikariConnectionMetric(meterRegistry);
        authorRepository.save(author);
        bookRepository.save(book);
    }
}
