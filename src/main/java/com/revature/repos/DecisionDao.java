package com.revature.repos;

import com.revature.models.Decisions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DecisionDao extends JpaRepository<Decisions, Integer> {

    List<Decisions> findByRoundIdAndUserGroupId(int roundId, int groupId);
}
