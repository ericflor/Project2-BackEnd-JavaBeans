package com.revature.repos;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    List<User> findByGroupId(int groupId);
    User findByGroupIdAndRoleId(int groupId, int roleId);
}
