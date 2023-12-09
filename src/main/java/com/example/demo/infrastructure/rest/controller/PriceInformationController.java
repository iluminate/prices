package com.example.demo.infrastructure.rest.controller;

import com.example.demo.application.usecase.PriceInformationService;
import com.example.demo.domain.model.dto.PriceInformationDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/prices")
public class PriceInformationController {
    private final PriceInformationService priceInformationService;

    public PriceInformationController(PriceInformationService priceInformationService) {
        this.priceInformationService = priceInformationService;
    }

    @GetMapping
    public ResponseEntity<PriceInformationDto> getPriceInformation(
            @RequestParam("applicationDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date applicationDate,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {
        PriceInformationDto result = priceInformationService.getBetweenCertainDatesByProductIdAndBrand(productId, brandId, applicationDate);
        return ResponseEntity.ok(result);
    }
}