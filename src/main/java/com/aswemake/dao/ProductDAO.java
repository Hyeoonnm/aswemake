package com.aswemake.dao;

import com.aswemake.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO  extends JpaRepository<ProductEntity, Long> {
}
