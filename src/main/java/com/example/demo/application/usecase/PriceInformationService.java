package com.example.demo.application.usecase;

import com.example.demo.domain.model.dto.PriceInformationDto;

import java.util.Date;

public interface PriceInformationService {
    PriceInformationDto getBetweenCertainDatesByProductIdAndBrand(Long productId, Long brandId, Date applicationDate);
}
