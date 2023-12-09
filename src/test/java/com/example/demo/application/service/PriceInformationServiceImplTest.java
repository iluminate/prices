package com.example.demo.application.service;

import com.example.demo.domain.model.PriceInformation;
import com.example.demo.domain.port.PriceInformationPort;
import com.example.demo.infrastructure.adapter.entity.PriceInformationEntity;
import com.example.demo.infrastructure.adapter.repository.PriceInformationRepository;
import com.example.demo.infrastructure.rest.controller.PriceInformationController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PriceInformationServiceImplTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @SpyBean
    private PriceInformationPort priceInformationPort;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testShouldOkWhenRequestAt10AMOn14th() throws Exception {
        performTest("2020-12-14T10:00:00", BigDecimal.valueOf(35.50));
    }

    @Test
    void testShouldOkWhenRequestAt4PMOn14th() throws Exception {
        performTest("2020-12-14T16:00:00", BigDecimal.valueOf(35.50));
    }

    @Test
    void testShouldOkWhenRequestAt9PMOn14th() throws Exception {
        performTest("2020-12-14T21:00:00", BigDecimal.valueOf(35.50));
    }

    @Test
    void testShouldOkWhenRequestAt10AMOn15th() throws Exception {
        performTest("2020-12-15T10:00:00", BigDecimal.valueOf(35.50));
    }

    @Test
    void testShouldOkWhenRequestAt9PMOn16th() throws Exception {
        performTest("2020-12-16T21:00:00", BigDecimal.valueOf(35.50));
    }

    private void performTest(String dateString, BigDecimal expectedPrice) throws Exception {
        Long productId = 35455L;
        Long brandId = 1L;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date applicationDate = dateFormat.parse(dateString);

        InputStream inputStream = getClass().getResourceAsStream("/prices.json");
        ObjectMapper objectMapper = new ObjectMapper();
        List<PriceInformation> prices = objectMapper.readValue(inputStream, new TypeReference<>() {
        });

        when(priceInformationPort.getByProductId(productId)).thenReturn(prices);

        mockMvc.perform(get("/api/prices")
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .param("applicationDate", dateFormat.format(applicationDate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(expectedPrice));
    }
}