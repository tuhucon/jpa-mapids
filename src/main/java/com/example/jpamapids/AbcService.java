package com.example.jpamapids;

import com.example.jpamapids.controller.Utils;
import com.example.jpamapids.entity.Author;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AbcService {

    private final AuthorRepository authorRepository;

    private final MeterRegistry meterRegistry;

//    @Transactional
    public void test() throws InterruptedException{
        System.out.println("start test method");
        Utils.printHikariConnectionMetric(meterRegistry);
        System.out.println("start query db");
        Utils.printHikariConnectionMetric(meterRegistry);
        Author author = authorRepository.findById(1L).get();
        System.out.println("end query db");
        Utils.printHikariConnectionMetric(meterRegistry);
        author.setAge(100);
        System.out.println("start save db");
        Utils.printHikariConnectionMetric(meterRegistry);
        authorRepository.save(author);
        System.out.println("end save db");
        Utils.printHikariConnectionMetric(meterRegistry);
    }
}
