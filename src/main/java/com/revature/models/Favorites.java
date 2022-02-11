package com.revature.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Favorites {

    private String imdbId;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private User user;

    public Favorites() {
    }

    public Favorites(String imdbId, User user) {
        this.imdbId = imdbId;
        this.user = user;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorites favorites = (Favorites) o;
        return Objects.equals(imdbId, favorites.imdbId) && Objects.equals(user, favorites.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imdbId, user);
    }

    @Override
    public String toString() {
        return "Favorites{" +
                "imdbId='" + imdbId + '\'' +
                ", user=" + user +
                '}';
    }
}
