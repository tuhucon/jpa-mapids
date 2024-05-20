package com.example.jpamapids;

import com.example.jpamapids.controller.Utils;
import com.example.jpamapids.entity.EnumTest;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.TableUI;

@Service
@RequiredArgsConstructor
public class EnumTestService {

    private final MeterRegistry meterRegistry;

    private final EnumTestServiceHelper enumTestServiceHelper;

    private final EnumTestRepository enumTestRepository;

    @Transactional(readOnly = true)
    public void transaction1() {
        Utils.printHikariConnectionMetric(meterRegistry);
        System.out.println("******** get id = 1");
        EnumTest enumTest = enumTestRepository.findById(1L).get();

        Utils.printHikariConnectionMetric(meterRegistry);
        System.out.println("********* save enum test");
        enumTest.setType2(EnumTest.Type.SAO_SAU);
        enumTestRepository.save(enumTest);

        System.out.println("********** call transaction 2:");
        enumTestServiceHelper.transaction2();;

        System.out.println("************* end transaction 2");
        Utils.printHikariConnectionMetric(meterRegistry);
    }

    @Service
    @RequiredArgsConstructor
    public static class EnumTestServiceHelper {

        private final MeterRegistry meterRegistry;

        private final EnumTestRepository enumTestRepository;

        @Transactional(propagation = Propagation.NOT_SUPPORTED)
        public void transaction2() {
            System.out.println("********** start transaction 2");
            Utils.printHikariConnectionMetric(meterRegistry);
            EnumTest enumTest = new EnumTest();

            System.out.println("*********** call save repo");
            enumTestRepository.save(enumTest);
            Utils.printHikariConnectionMetric(meterRegistry);
        }
    }
}
