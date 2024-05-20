package com.example.jpamapids;

import com.example.jpamapids.controller.Utils;
import com.example.jpamapids.entity.Projection;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class ProjectionService {

    private final ProjectionRepository projectionRepository;

    private final MeterRegistry meterRegistry;

    private final ProjectionServiceHelper projectionServiceHelper;


    public void getAndUPdateId1And2() {
        projectionServiceHelper.getAndUpdateId1();
        projectionServiceHelper.getAndUpdateId2();
    }

    @Transactional
    public void outerTransaction() {
        System.out.println("Start outer transaction");
        Projection p = new Projection();
        p.setCol1("col1");
        p.setCol2("col2");
        p.setCol3("col3");
        projectionRepository.save(p);
        Utils.printHikariConnectionMetric(meterRegistry);
        System.out.println("start inner transaction");
        projectionServiceHelper.getAndUpdateId1();
        System.out.println("end inner transaction");
        Utils.printHikariConnectionMetric(meterRegistry);
        System.out.println("end outer transaction");
    }

    @Service
    @RequiredArgsConstructor
    public static class ProjectionServiceHelper {

        private final ProjectionRepository projectionRepository;

        private final MeterRegistry meterRegistry;

        @Transactional(propagation = Propagation.REQUIRES_NEW)
        public void getAndUpdateId1() {
            System.out.println("connection pool when run in service");
            Projection projection = projectionRepository.findById(1L).get();
            projection.setCol2(ThreadLocalRandom.current().nextInt(0, 1000) + "");
            Utils.printHikariConnectionMetric(meterRegistry);
        }

        @Transactional
        public void getAndUpdateId2() {
            System.out.println("connection pool when run in service");
            Projection projection = projectionRepository.findById(2L).get();
            projection.setCol2(ThreadLocalRandom.current().nextInt(0, 1000) + "");
            Utils.printHikariConnectionMetric(meterRegistry);
        }
    }
}
