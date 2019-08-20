package com.flaya.repository;

import com.flaya.domain.GameInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameInfo, Integer> {
}
