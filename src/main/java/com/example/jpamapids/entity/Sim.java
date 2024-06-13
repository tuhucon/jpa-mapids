package com.example.jpamapids.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Sim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String tel;

    Float total;

    Integer col1; //Thế vận

    Integer col2; //Tài lộc

    Integer col3; //Công danh

    Integer col4; //Tình cảm

    Integer col5; //Gia đạo

    Integer col6; //Thi củ
}
