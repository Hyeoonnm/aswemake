package com.aswemake.dao;

import com.aswemake.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberDAO extends JpaRepository<MemberEntity, Long> {
    boolean existsByLoginId(String loginId);
    Optional<MemberEntity> findByLoginId(String loginId);

}
