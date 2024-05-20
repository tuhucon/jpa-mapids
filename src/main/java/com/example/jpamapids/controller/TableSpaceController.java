package com.example.jpamapids.controller;

import com.example.jpamapids.AuthorRepository;
import com.example.jpamapids.BookRepository;
import com.example.jpamapids.ChildRepository;
import com.example.jpamapids.HouseRepository;
import com.example.jpamapids.ParentRepository;
import com.example.jpamapids.entity.Author;
import com.example.jpamapids.entity.Book;
import com.example.jpamapids.entity.House;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
public class TableSpaceController {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    private final HouseRepository houseRepository;

    private final ChildRepository childRepository;

    private final ParentRepository parentRepository;

    @GetMapping("/checkSelect")
    @Transactional
    public void checkSelect() throws InterruptedException{
        Author author = authorRepository.findById(1L).get();
        Book book = bookRepository.findById(1L).get();
        House house = houseRepository.findById(1L).get();

        author.setAge(ThreadLocalRandom.current().nextInt(0, 130));
        book.setTitle("title: " + ThreadLocalRandom.current().nextInt(0, 100));
        house.setName("name: " + ThreadLocalRandom.current().nextInt(0, 100));

        //Select with jpql
        System.out.println("*********** start select parent ************");
        parentRepository.findParentWithIdLessThan(3L);
        System.out.println("************* end select parent **************");

        //Select with natvie query - without query hint ---> flush all update
        //Select with native query having query hint ---->
        System.out.println("************* start select child ****************");
        childRepository.findChildWithIdLessThan(3L);
        System.out.println("************* end select child ********************");

        //Select with jpql
        System.out.println("************** start select book **************");
        bookRepository.findBookWithIdLessThan(3L); //Hibernate will flush all changes before this command based on query space
        System.out.println("*************** send select book **************");
        //Select with raw sql
        System.out.println("***************** start select author **************");
        authorRepository.findAuthorWithIdLessThan(3L);
        System.out.println(("**************** end select author **************"));
    }

}
