package com.example.jpamapids.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Phone {

    @ManyToOne
    @JoinColumn
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    Author author;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String number;
}
