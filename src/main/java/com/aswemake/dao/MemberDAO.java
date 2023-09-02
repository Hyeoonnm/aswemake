package com.aswemake.dao;

import com.aswemake.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDAO extends JpaRepository<MemberEntity, Long> {
    MemberEntity findByName(String entity);
}
