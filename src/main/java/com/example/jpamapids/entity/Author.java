package com.example.jpamapids.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
//@NamedEntityGraph(
//        name = "author-book-graph",
//        attributeNodes = {
//                @NamedAttributeNode("books")
//        }
//)
public class Author extends AbstractAggregateRoot<Author> {

    Integer age;

    @OneToMany(mappedBy = "author")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    List<Book> books = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //    @Basic(fetch = FetchType.LAZY)
    String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonManagedReference
    List<Phone> phones = new ArrayList<>();

    @Version
    Integer version;

    public Author() {
        super();
//        registerEvent(new AuthorCreatedEvent(this));
    }
}
