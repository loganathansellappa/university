package com.learning.university.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.university.database.dto.CollegeDto;
import com.learning.university.service.CollegeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CollegeController.class)
public class CollegeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CollegeService collegeService;

    @Test
    public void testGetCollege() throws Exception {
        int collegeId = 1;
        CollegeDto college = new CollegeDto();
        college.setId(collegeId);
        college.setName("Example College");

        Mockito.when(collegeService.findById(collegeId)).thenReturn(college);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/colleges/{id}", collegeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(collegeId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Example College"));
    }

    @Test
    public void testGetCollegeNotFound() throws Exception {
        int collegeId = 2;

        Mockito.when(collegeService.findById(collegeId)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/colleges/{id}", collegeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetCreateCollege() throws Exception {
        // Arrange
        CollegeDto collegeDto = new CollegeDto();
        collegeDto.setId(1);
        collegeDto.setName("New College");

        CollegeDto createdCollege = new CollegeDto();
        createdCollege.setId(1);
        createdCollege.setName("New College");
        Mockito.when(collegeService.createCollege(Mockito.any(CollegeDto.class))).thenReturn(createdCollege);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/colleges")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(collegeDto)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("New College"));
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
