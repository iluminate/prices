package com.example.demo.application.service;

import com.example.demo.application.mapper.PriceInformationDtoMapper;
import com.example.demo.application.usecase.PriceInformationService;
import com.example.demo.domain.model.PriceInformation;
import com.example.demo.domain.model.dto.PriceInformationDto;
import com.example.demo.domain.port.PriceInformationPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;

@Service
public class PriceInformationServiceImpl implements PriceInformationService {
    private final PriceInformationPort priceInformationPort;

    @Autowired
    public PriceInformationServiceImpl(PriceInformationPort priceInformationPort) {
        this.priceInformationPort = priceInformationPort;
    }

    @Override
    public PriceInformationDto getBetweenCertainDatesByProductIdAndBrand(Long productId, Long brandId, Date applicationDate) {
        var pricesByProductId = priceInformationPort.getByProductId(productId);
        var filteredPrices = pricesByProductId.stream()
                .filter(price -> price.getStartedAt().before(applicationDate) && price.getEndedAt().after(applicationDate))
                .sorted(Comparator.comparing(PriceInformation::getPriority))
                .toList();
        var pricePriority = filteredPrices.getFirst();
        return PriceInformationDtoMapper.toDto(pricePriority);
    }
}