package com.example.jpamapids.event;

import com.example.jpamapids.entity.Author;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class AuthorCreatedEvent extends ApplicationEvent {

    public AuthorCreatedEvent(Author author) {
        super(author);
    }

    public AuthorCreatedEvent(Author author, Clock clock) {
        super(author, clock);
    }

    public Author getAuthor() {
        return (Author) this.source;
    }
}
