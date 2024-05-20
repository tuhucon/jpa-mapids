package com.example.jpamapids.controller;

import com.example.jpamapids.AuthorRepository;
import com.example.jpamapids.ParentRepository;
import com.example.jpamapids.entity.Author;
import com.example.jpamapids.entity.Parent;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
public class InsertOrderController {

    private final AuthorRepository authorRepository;

    private final ParentRepository parentRepository;

    @GetMapping("/insertOrder")
    @Transactional
    public void insertOrder() {
        parentRepository.save(Parent.builder().title("chich choe").id(36L).build());
        authorRepository.save(Author.builder().name("hoa hoe").id(1016L).build());
        authorRepository.save(Author.builder().name("abasdf").id(1017L).build());
        parentRepository.save(Parent.builder().title("abc").id(37L).build());

    }
}
