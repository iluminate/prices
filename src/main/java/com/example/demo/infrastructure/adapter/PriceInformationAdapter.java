package com.example.demo.infrastructure.adapter;

import com.example.demo.domain.model.PriceInformation;
import com.example.demo.domain.port.PriceInformationPort;
import com.example.demo.infrastructure.adapter.mapper.PriceInformationDboMapper;
import com.example.demo.infrastructure.adapter.repository.PriceInformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceInformationAdapter implements PriceInformationPort {

    private final PriceInformationRepository priceInformationRepository;

    public PriceInformationAdapter(PriceInformationRepository priceInformationRepository) {
        this.priceInformationRepository = priceInformationRepository;
    }

    @Override
    public List<PriceInformation> getByProductId(Long productId) {
        var priceInformation = priceInformationRepository.findByProductId(productId);
        return priceInformation.stream().map(PriceInformationDboMapper::toDomain).toList();
    }
}
