package com.learning.university.database.mapper;

import com.learning.university.database.dto.CollegeDto;
import com.learning.university.database.entity.College;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface CollegeMapper {

    College toEntity(CollegeDto collegeDto);

    CollegeDto toDto(College college);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    College partialUpdate(CollegeDto collegeDto, @MappingTarget College college);
}