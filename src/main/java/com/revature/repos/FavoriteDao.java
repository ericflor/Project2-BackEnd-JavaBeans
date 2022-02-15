package com.revature.repos;

import com.revature.models.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteDao extends JpaRepository<Favorites, Integer> {

}
