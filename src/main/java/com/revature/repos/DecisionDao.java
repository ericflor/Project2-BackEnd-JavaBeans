package com.revature.repos;

import com.revature.models.Decisions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DecisionDao extends JpaRepository<Decisions, Integer> {
}
