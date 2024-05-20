package com.example.jpamapids;

import com.example.jpamapids.entity.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @EntityGraph(value = "author-book-graph")
    default Author tuhucon(Long id) {
        return findById(id).get();
    }

    @EntityGraph(value = "author-book-graph")
    @Override
    List<Author> findAll();

    @EntityGraph(attributePaths = {"books"})
    @Query(value = "select a from Author a where a.id = ?1")
    Optional<Author> chichchoe(Long id);

    @EntityGraph(attributePaths = "phones")
    @Query(value = "select a from Author a where a.id = ?1")
    Optional<Author> tacket(Long id);

    @Query(value = "select * from Author where id < :id", nativeQuery = true)
    List<Author> findAuthorWithIdLessThan(Long id);


    @Query(value = "select distinct a from Author a left join fetch a.books where a.id in :ids")
    List<Author> findAuthorFetchBook(List<Long> ids);
}
