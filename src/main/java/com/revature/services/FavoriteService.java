package com.revature.services;

import com.revature.models.Favorites;
import com.revature.models.Group;
import com.revature.models.User;
import com.revature.repos.FavoriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    private FavoriteDao favoriteDao;

    @Autowired
    public FavoriteService(FavoriteDao favoriteDao){
        this.favoriteDao = favoriteDao;
    }

//    public List<Favorites> getFavs(int id){
//        Optional<Favorites> favs = favoriteDao.findAllById(id);
//        if(favs.isPresent()){
//            return (List<Favorites>) favs.get();
//        }
//        return (List<Favorites>) new Favorites();
//    }

    public boolean saveFav(Favorites favorites){
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
