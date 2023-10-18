package com.learning.university.service;

import com.learning.university.database.dto.CollegeDto;
import com.learning.university.database.entity.College;
import com.learning.university.database.mapper.CollegeMapper;
import com.learning.university.database.repository.CollegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CollegeService {

    private final CollegeMapper collegeMapper;
    private final CollegeRepository collegeRepository;
    public CollegeDto findById(Integer id) {
        College college = collegeRepository.findById(id).orElse(null);
        if (college != null) {
            return collegeMapper.toDto(college);
        }
        return null;
    }

    @Transactional
    public CollegeDto createCollege(CollegeDto collegeDto) {
        try {
            College college = collegeMapper.toEntity(collegeDto);
            college = collegeRepository.save(college);
            return collegeMapper.toDto(college);
        } catch (Exception e) {
            // Log the error here
            // You can use your preferred logging framework, for example:
            // logger.error("Failed to create college", e);
            throw new RuntimeException("Failed to create college", e);
        }
    }
}