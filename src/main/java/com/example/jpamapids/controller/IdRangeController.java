package com.example.jpamapids.controller;

import com.example.jpamapids.EnumTestRepository;
import com.example.jpamapids.IdRangeRepository;
import com.example.jpamapids.IdRangeService;
import com.example.jpamapids.entity.Book;
import com.example.jpamapids.entity.EnumTest;
import com.example.jpamapids.entity.IdRange;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
public class IdRangeController {

    private final IdRangeRepository idRangeRepository;

    private final IdRangeService idRangeService;

    private final EnumTestRepository enumTestRepository;

    @GetMapping("/idrange")
    @Transactional
    public void idrange(@RequestParam boolean lock) throws InterruptedException {
        IdRange idRange;
        if (lock) {
            idRange = idRangeRepository.findOneByType(1L); // select for update
        } else {
            idRange = idRangeRepository.getOneByType(1L); // select no for update
        }
        idRange.setCount(idRange.getCount() + 1);
        idRangeRepository.save(idRange);
        Thread.sleep(ThreadLocalRandom.current().nextLong(200L, 300L)); // sleep 200 ~ 300 ml
    }

    @GetMapping("/idrange1")
    public void idrange1() throws InterruptedException {
        idRangeService.transaction1(); // select for update
        idRangeService.transaction2();
    }

    @GetMapping("/idrange2")
    @Transactional
    public void idrange2() throws InterruptedException {
        EnumTest enumTest = enumTestRepository.findById(1L).get();
        idRangeService.transactionRequireNew(); // select for update in new connection
        Thread.sleep(ThreadLocalRandom.current().nextLong(200L, 300L));
    }
}
