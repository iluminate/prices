package com.example.demo.infrastructure.adapter.repository;

import com.example.demo.infrastructure.adapter.entity.PriceInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceInformationRepository extends JpaRepository<PriceInformationEntity, Long> {
    List<PriceInformationEntity> findByProductId(Long productId);
}