package com.technicaltest.repo;

import com.technicaltest.entity.MovementSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementSpecRepository extends JpaRepository<MovementSpec, Long> {
    List<MovementSpec> findByOrderByFieldPositionAsc();
}
