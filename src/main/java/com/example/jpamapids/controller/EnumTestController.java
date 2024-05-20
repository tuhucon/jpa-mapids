package com.example.jpamapids.controller;

import com.example.jpamapids.EnumTestRepository;
import com.example.jpamapids.EnumTestService;
import com.example.jpamapids.entity.EnumTest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EnumTestController {

    private final EnumTestRepository enumTestRepository;

    private final EnumTestService enumTestService;

    @GetMapping("/enumTest")
    public EnumTest enumTest() {
        EnumTest enumTest = new EnumTest();
        enumTest.setType1(EnumTest.Type.TUHUCON);
        enumTest.setType2(EnumTest.Type.TUHUCON);
        enumTestRepository.save(enumTest);

        var xxx = enumTestRepository.findById(1L).get();
        return xxx;
    }

    @GetMapping("/tranTest")
    public void tranTest() {
        enumTestService.transaction1();
    }
}
