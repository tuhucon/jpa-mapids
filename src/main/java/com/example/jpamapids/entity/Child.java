package com.example.jpamapids.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Child {

    @Id
    Long id;

    @OneToOne()
    @JoinColumn(name = "id")
    @MapsId
    Parent parent;

    @Column
    String content;
}
