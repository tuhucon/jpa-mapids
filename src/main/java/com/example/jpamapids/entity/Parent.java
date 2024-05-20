package com.example.jpamapids.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Parent {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

//    @OneToOne(mappedBy = "parent", optional = false, fetch = FetchType.LAZY)
//    Child child;

    @Column
    String title;
}
