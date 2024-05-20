package com.example.jpamapids.event;

import com.example.jpamapids.AuthorRepository;
import com.example.jpamapids.BookRepository;
import com.example.jpamapids.controller.Utils;
import com.example.jpamapids.entity.Author;
import com.example.jpamapids.event.AuthorCreatedEvent;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class AuthorEventListener {

    private final MeterRegistry meterRegistry;

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    @TransactionalEventListener(AuthorCreatedEvent.class)
    @Transactional()
    @Async
    public void authorCreatedEvenHandler(AuthorCreatedEvent event) {
        System.out.println("********start event in: " + Thread.currentThread().getName());
        Utils.printHikariConnectionMetric(meterRegistry);

        Author author = event.getAuthor();
        author.setName("bim bip");
        System.out.println("********sleep 40s then update author");
        try {
            Thread.sleep(40_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        authorRepository.save(author);

        System.out.println(("*******end event in: " + Thread.currentThread().getName()));
        Utils.printHikariConnectionMetric(meterRegistry);
    }

}
