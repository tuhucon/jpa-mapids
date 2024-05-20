package com.example.jpamapids.controller;

import com.example.jpamapids.TestRepository;
import com.example.jpamapids.entity.Author;
import com.example.jpamapids.AuthorRepository;
import com.example.jpamapids.entity.Book;
import com.example.jpamapids.BookRepository;
import com.example.jpamapids.entity.Child;
import com.example.jpamapids.ChildRepository;
import com.example.jpamapids.entity.Parent;
import com.example.jpamapids.ParentRepository;
import com.example.jpamapids.entity.Test;
import com.zaxxer.hikari.pool.HikariPool;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
public class HelloController {

    final ParentRepository parentRepository;

    final ChildRepository childRepository;

    final AuthorRepository authorRepository;

    final BookRepository bookRepository;

    final TestRepository testRepository;

    @GetMapping("/tuhucon123")
    public void addTest() {
//        for (int i = 0; i <= 1_000_000; i++) {
//            Test test = new Test();
//            test.setName("tu hu con: " + i);
//            test.setStatus(i % 2);
//            test.setAge(i / 10);
//            testRepository.save(test);
//        }
        System.out.println(testRepository.findNameByIdJapl(1L));
    }

    @GetMapping("/create")
    public String createChild() throws InterruptedException {
        Parent p = new Parent();
        p.setTitle("title 1");
        Child c = Child.builder()
                .content("tu hu con")
                .parent(p)
                .build();
        System.out.println("********************************");
        childRepository.save(c);

        return "OK";
    }

    @GetMapping("/selectParentChild")
    public String selectParentChild(@RequestParam Long id) throws InterruptedException {
        Parent p = parentRepository.findById(id).get();
        System.out.println("*************************************");
        childRepository.findById(p.getId());
        return "OK";
    }

    @GetMapping("/selectChildParent")
    public String selectChildParent(@RequestParam Long id) throws InterruptedException {
        Child c = childRepository.findById(id).get();
        System.out.println("*************************************");
        parentRepository.findById(id);
        return "OK";
    }

    @GetMapping("/deleteChild")
    public String deleteChild(@RequestParam Long id) {
        Child c = childRepository.findById(id).get();
        childRepository.delete(c);
        return "OK";
    }

    @GetMapping("/deleteParent")
    public String deleteParent(@RequestParam Long id) {
        Parent p = parentRepository.findById(id).get();
        parentRepository.delete(p);
        return "OK";
    }

    @GetMapping("/selectAuthor")
    public String getAuthor(@RequestParam Long id) {
        Author a = authorRepository.findById(id).get();
        for (var book : a.getBooks()) {
            System.out.println(book);
        }
        return "OK";
    }

    @GetMapping("/insertAuthorAndBook")
    public void insertAuthorAndBook() {
        Author a = new Author();
        a.setName("tu hu con");
        a.setAge(ThreadLocalRandom.current().nextInt(1, 100));

        Book b1 = new Book();
        b1.setIsbn("isbn");
        b1.setTitle("title");
        a.getBooks().add(b1);

        Book b2 = new Book();
        b2.setIsbn("isbn");
        b2.setTitle("title");
        a.getBooks().add(b2);

        bookRepository.save(b1);
        bookRepository.save(b2);
        authorRepository.save(a);
    }
}
