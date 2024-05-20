package com.example.jpamapids.controller;

import com.example.jpamapids.ProjectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectionController {

    private final ProjectionRepository projectionRepository;

    @GetMapping("/projection")
    public void projection() {
//        projectionRepository.findAllViaJpql(); //select * from projection
//        projectionRepository.findAllViaProjectionDTOJpql(); //select * from projection
//        projectionRepository.findAllViaProjectionDTOJpqlSelectCol1(); //select col1 from projection
//        projectionRepository.findAllByCol1("col1"); //select col1 from projection due to return ProjectionDTO
    }
}
