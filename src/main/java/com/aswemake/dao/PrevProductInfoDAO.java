package com.aswemake.dao;

import com.aswemake.entity.PrevProductInfoEntity;
import com.aswemake.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrevProductInfoDAO extends JpaRepository<PrevProductInfoEntity, Long> {
    void deleteByProductId(Long id);
    List<PrevProductInfoEntity> findAllByProductId(Long id);

    PrevProductInfoEntity findByProductId(Long id);
}
