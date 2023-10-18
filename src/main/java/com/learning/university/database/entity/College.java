package com.learning.university.database.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = College.TABLE_NAME)
public class College {
    public static final String TABLE_NAME = "college";
    public static final String COLUMN_ID_NAME = "id";
    public static final String COLUMN_NAME_NAME = "name";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_ID_NAME)
    Integer id;

    @Column(name = COLUMN_NAME_NAME)
    String name;

}