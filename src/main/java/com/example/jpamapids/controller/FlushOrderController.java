package com.example.jpamapids.controller;

import com.example.jpamapids.AuthorRepository;
import com.example.jpamapids.entity.Book;
import com.example.jpamapids.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FlushOrderController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @GetMapping("/flush")
    @Transactional
    public void flush(@RequestParam Long id) {
        Book b1 = bookRepository.findById(id).get();
        bookRepository.delete(b1);

        Book b2 = bookRepository.findById(2L).get();
        bookRepository.delete(b2);

        Book b = new Book();
        b.setTitle("isbn = " + id);
        b.setIsbn(id.toString());
        bookRepository.save(b);
    }

    @GetMapping("/flushMode")
    @Transactional
    public void flushMode() {
        Book b1 = bookRepository.findById(1L).get();
        b1.setTitle("b1");
        authorRepository.findAll();
        bookRepository.findAll();
    }
}
