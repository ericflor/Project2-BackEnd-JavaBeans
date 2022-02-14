package com.revature.repos;

import com.revature.models.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteDao extends JpaRepository<Favorites, Integer> {
}
