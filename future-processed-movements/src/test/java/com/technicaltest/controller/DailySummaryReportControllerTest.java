package com.technicaltest.controller;

import com.technicaltest.service.MovementsService;
import com.technicaltest.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DailySummaryReportController.class)
class DailySummaryReportControllerTest {

    @MockBean
    private MovementsService movementsService;

    private MockMvc mockMvc;

    @Autowired
    DailySummaryReportControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void getDailySummaryReportTest() throws Exception {
        when(movementsService.getDailySummaryReport(anyString())).thenReturn(TestUtils.getDailySummaryReportMock());

        mockMvc.perform(get("/api/v1/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$").isArray());
    }
}