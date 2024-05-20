package com.example.jpamapids;

import com.example.jpamapids.entity.EnumTest;
import com.example.jpamapids.entity.IdRange;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class IdRangeService {

    private final IdRangeRepository idRangeRepository;

    private final EnumTestRepository enumTestRepository;

    @Transactional
    public void transaction1() {
        IdRange idRange = idRangeRepository.findOneByType(1L);
        idRange.setCount(idRange.getCount() + 1);
        idRangeRepository.save(idRange);
    }

    @Transactional
    public void transaction2() throws InterruptedException {
        EnumTest enumTest = enumTestRepository.findById(1L).get();
        Thread.sleep(ThreadLocalRandom.current().nextLong(200L, 300L));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void transactionRequireNew() {
        IdRange idRange = idRangeRepository.findOneByType(1L);
        idRange.setCount(idRange.getCount() + 1);
        idRangeRepository.save(idRange);
    }
}
