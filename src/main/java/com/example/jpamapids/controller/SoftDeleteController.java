package com.example.jpamapids.controller;

import com.example.jpamapids.ProjectionRepository;
import com.example.jpamapids.entity.Projection;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SoftDeleteController {

    private final ProjectionRepository projectionRepository;

    @GetMapping("/softdelete")
    public void softdelete() {
        List<Projection> projections = projectionRepository.findAll();
        System.out.println(projections);
        Projection p = projections.get(0);
        projectionRepository.delete(p);

        projections = projectionRepository.findAll();
        System.out.println(projections);
    }
}
