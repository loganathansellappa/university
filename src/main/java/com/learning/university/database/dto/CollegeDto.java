package com.learning.university.database.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.learning.university.database.entity.College}
 */
@Data
public class CollegeDto implements Serializable {

    public Integer id;
    public String name;
}