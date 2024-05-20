package com.example.jpamapids;

import com.example.jpamapids.entity.Book;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends NaturalRepository<Book, Long> {

    @Query("select b from Book b where b.id < :id")
    List<Book> findBookWithIdLessThan(Long id);

}
