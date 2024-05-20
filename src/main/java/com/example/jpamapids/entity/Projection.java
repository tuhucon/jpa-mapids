package com.example.jpamapids.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Where(clause = "deleted = 0")
@SQLDelete(sql = "update projection set deleted = 1 where id = ?")
public class Projection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String col1;

    String col2;

    String col3;

    boolean deleted = false;
}
