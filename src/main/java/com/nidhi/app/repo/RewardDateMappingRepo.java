/*
 * Copyright (c) 2021
 */

package com.nidhi.app.repo;

import com.nidhi.app.model.RewardDateMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Ricky
 * @project gaming-service
 */
@Repository
public interface RewardDateMappingRepo extends JpaRepository<RewardDateMapping, Integer> {
    Optional<RewardDateMapping> findByDayIndex(int dayIndex);
}
