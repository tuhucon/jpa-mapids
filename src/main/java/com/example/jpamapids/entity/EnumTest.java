package com.example.jpamapids.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class EnumTest {

    public static enum Type {
        TUHUCON, CHICH_CHOE, SAO_SAU;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(value = EnumType.ORDINAL)
    Type type1;

    @Enumerated(value = EnumType.STRING)
    Type type2;
}
