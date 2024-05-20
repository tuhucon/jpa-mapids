package com.example.jpamapids.controller;

import com.example.jpamapids.AuthorRepository;
import com.example.jpamapids.entity.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FetchJoinController {

    private final AuthorRepository authorRepository;

    @GetMapping("/joinFetchAuthor")
    public void joinFetchAuthor() {
        List<Author> authors =authorRepository.findAuthorFetchBook(List.of(1L, 2L));
        for (Author a: authors) {
            System.out.println(a.getId());
            System.out.println(a.getBooks());
            System.out.println("---------------------------------------");
        }
    }
}
