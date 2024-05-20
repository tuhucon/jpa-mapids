package com.example.jpamapids.controller;

import com.example.jpamapids.HouseRepository;
import com.example.jpamapids.RoomRepository;
import com.example.jpamapids.entity.House;
import com.example.jpamapids.entity.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UniDirectionController {

    private final HouseRepository houseRepository;
    private final RoomRepository roomRepository;

    @GetMapping("/uniInsert")
    @Transactional
    public void insertHouse() {
//        House house = new House();
//        house.setName("my house");
//
//        house.getRooms().add(Room.builder().name("room 1").build());
//        house.getRooms().add(Room.builder().name("room 2").build());
//        house.getRooms().add(Room.builder().name("room 3").build());
//
//        houseRepository.save(house);

        House house = houseRepository.findById(2L).get();

        house.getRooms().add(Room.builder().name("room 4").build());

        houseRepository.save(house);
    }

}
