package com.learning.university.controller;

import com.learning.university.database.dto.CollegeDto;
import com.learning.university.service.CollegeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/colleges")
public class CollegeController {
    private CollegeService collegeService;

    @GetMapping("{id}")
    public ResponseEntity<CollegeDto> getCollege(@PathVariable Integer id) {
        CollegeDto college = collegeService.findById(id);
        return new ResponseEntity<>(college, HttpStatus.OK);
    }

    // create api to create a new college which uses the college service to create a new college
    // and returns the created college with status code 201 on success and 400 for failure
    // remove ID from the request body

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
