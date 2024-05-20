package com.example.jpamapids.controller;

import com.example.jpamapids.AuthorRepository;
import com.example.jpamapids.entity.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EntityGraphsController {

    private final AuthorRepository authorRepository;

    @GetMapping("/graph/loadParent")
    public void loadParent(@RequestParam boolean useGraph) {
        if (useGraph == false) {
            Author author2 = authorRepository.findById(2L).get();
            author2.getName();
        } else {
            Author author2 = authorRepository.chichchoe(2L).get();
//            author2 = authorRepository.tacket(2L).get();

            System.out.println("phone = " + author2.getPhones().size());
            System.out.println("book = " + author2.getBooks().size());
        }
    }


}
