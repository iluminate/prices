package com.example.demo.application.mapper;

import com.example.demo.domain.model.PriceInformation;
import com.example.demo.domain.model.dto.PriceInformationDto;

public class PriceInformationDtoMapper {
    public static PriceInformationDto toDto(PriceInformation domain) {
        PriceInformationDto dto = new PriceInformationDto();
        dto.setBrandId(domain.getBrandId());
        dto.setListPrice(domain.getListPrice());
        dto.setProductId(domain.getProductId());
        dto.setPrice(domain.getPrice());
        return dto;
    }
}
