package com.revature.repos;

import com.revature.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupDao extends JpaRepository<Group, Integer> {
}
