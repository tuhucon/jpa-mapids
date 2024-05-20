package com.example.jpamapids.controller;

import com.example.jpamapids.AuthorRepository;
import com.example.jpamapids.ProjectionRepository;
import com.example.jpamapids.ProjectionService;
import com.example.jpamapids.entity.Author;
import com.example.jpamapids.entity.Projection;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConnectionController {

    private final ProjectionService projectionService;
    private final MeterRegistry meterRegistry;

    private final AuthorRepository authorRepository;

    @GetMapping("/connection")
    @Transactional
    public void connection() throws InterruptedException {
        projectionService.getAndUPdateId1And2();
    }
}
