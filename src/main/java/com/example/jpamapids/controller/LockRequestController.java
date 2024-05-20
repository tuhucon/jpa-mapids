package com.example.jpamapids.controller;

import com.example.jpamapids.LockRequestRepository;
import com.example.jpamapids.entity.LockRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class LockRequestController {

    private final LockRequestRepository lockRequestRepository;

    @GetMapping("/lockRequest")
    public void lockRequest() {
        if (lockRequestRepository.existsById(100L) == false){
            LockRequest lockRequest1 = new LockRequest();
            lockRequest1.setId(100L);
            lockRequest1.setCreatedDate(new Date());
            try {
                lockRequestRepository.createNew(lockRequest1.getId(), lockRequest1.getCreatedDate());
            } catch (Exception ex) {
                System.out.println(ex);
                System.out.println("request lock fail");
                return;
            }
            System.out.println("request lock ok");
        } else {
            System.out.println("request lock fail");
        }
    }

}
