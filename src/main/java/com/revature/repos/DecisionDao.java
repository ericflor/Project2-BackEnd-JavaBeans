package com.revature.repos;

import com.revature.models.Decisions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DecisionDao extends JpaRepository<Decisions, Integer> {
//    int countByTitleAndChoiceAndUserGroupIdAndRound(String title, boolean choice, int groupId, int round)
//            ;
    @Query
}
