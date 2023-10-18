package com.learning.university.controller;

import com.learning.university.common.exceptions.ResourceNotFoundException;
import com.learning.university.database.dto.CollegeDto;
import com.learning.university.service.CollegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/colleges")
public class CollegeController {
    private final CollegeService collegeService;

    @GetMapping("{id}")
    public ResponseEntity<?> getCollege(@PathVariable Integer id) {
        CollegeDto college = collegeService.findById(id);
        if (college == null) {
            throw new ResourceNotFoundException("college not found");
        }
        return new ResponseEntity<>(college, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CollegeDto> createCollege(@RequestBody CollegeDto collegeDto) {
        try {
            CollegeDto createdCollege = collegeService.createCollege(collegeDto);
            return new ResponseEntity<>(createdCollege, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
