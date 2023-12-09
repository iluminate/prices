package com.example.demo.infrastructure.adapter.mapper;

import com.example.demo.domain.model.PriceInformation;
import com.example.demo.infrastructure.adapter.entity.PriceInformationEntity;

public class PriceInformationDboMapper {
    public static PriceInformation toDomain(PriceInformationEntity entity) {
        PriceInformation domain = new PriceInformation();
        domain.setId(entity.getId());
        domain.setBrandId(entity.getBrandId());
        domain.setStartedAt(entity.getStartedAt());
        domain.setEndedAt(entity.getEndedAt());
        domain.setListPrice(entity.getListPrice());
        domain.setProductId(entity.getProductId());
        domain.setPriority(entity.getPriority());
        domain.setPrice(entity.getPrice());
        domain.setCurrency(entity.getCurrency());
        return domain;
    }
}
