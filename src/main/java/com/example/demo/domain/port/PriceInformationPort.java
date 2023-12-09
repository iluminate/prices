package com.example.demo.domain.port;

import com.example.demo.domain.model.PriceInformation;

import java.util.List;

public interface PriceInformationPort {
    List<PriceInformation> getByProductId(Long productId);
}
