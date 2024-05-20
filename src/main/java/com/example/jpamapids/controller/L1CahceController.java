package com.example.jpamapids.controller;

import com.example.jpamapids.entity.Book;
import com.example.jpamapids.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class L1CahceController {

    private final BookRepository bookRepository;

    @GetMapping("/uniqueIndex")
    @Transactional
    public void uniqueIndex() {
        Book id1 = bookRepository.findById(1L).get();
        Book id2 = bookRepository.findById(2L).get();
        Book id3 = bookRepository.findById(1L).get();

        id1.setIsbn("00");
        bookRepository.save(id1);

        Book isbn1 = bookRepository.findBySimpleNaturalId("00").get();
        Book isbn2 = bookRepository.findBySimpleNaturalId("2").get();
        Book isbn11 = bookRepository.findBySimpleNaturalId("00").get();

    }

}
