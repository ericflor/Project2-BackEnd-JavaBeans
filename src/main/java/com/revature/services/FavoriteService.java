package com.revature.services;

import com.revature.models.Favorites;
import com.revature.repos.FavoriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FavoriteService {

    private FavoriteDao favoriteDao;

    @Autowired
    public FavoriteService(FavoriteDao favoriteDao){
        this.favoriteDao = favoriteDao;
    }


    public boolean saveFav(Favorites favorites){ // use movie title instead of exact movie id and then just
        // display the movie title in the front end as a list under the user profile section for favorites
        try {
            favoriteDao.save(favorites);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // testing - to be deleted
    public List<Favorites> getAllFavorites(){
        return favoriteDao.findAll();
    }
}
