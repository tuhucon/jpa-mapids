package com.example.jpamapids.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class LockRequest {

    @Id
    Long id;

    Date createdDate;
}
