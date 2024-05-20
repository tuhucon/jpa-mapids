package com.example.jpamapids.controller;

import com.example.jpamapids.HouseRepository;
import com.example.jpamapids.RoomRepository;
import com.example.jpamapids.entity.House;
import com.example.jpamapids.entity.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManyToManyController {

    private final HouseRepository houseRepository;
    private final RoomRepository roomRepository;

    @GetMapping("/deleteManyChild")
    @Transactional
    public void deleteManyChild() {
        House house = houseRepository.findById(2L).get();
        Room room = roomRepository.findById(3L).get();
        room.getHouses().remove(house);
        house.getRooms().remove(room);
    }
}
