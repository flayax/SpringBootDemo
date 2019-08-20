package com.flaya.repository;

import com.flaya.domain.GirlInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GirlRepository extends JpaRepository<GirlInfo, Integer> {
    // 通过年龄查询
    public List<GirlInfo> findByAge(Integer age);
}
