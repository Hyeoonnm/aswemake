package com.aswemake.dao;

import com.aswemake.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersDAO extends JpaRepository<OrdersEntity, Long> {
}
