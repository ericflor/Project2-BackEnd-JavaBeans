package com.revature.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int favId;
    private String imdbId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public Favorites() {
    }

    public Favorites(int favId, String imdbId, User user) {
        this.favId = favId;
        this.imdbId = imdbId;
        this.user = user;
    }

    public int getFavId() {
        return favId;
    }

    public void setFavId(int favId) {
        this.favId = favId;
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
        return getFavId() == favorites.getFavId() && Objects.equals(getImdbId(), favorites.getImdbId()) && Objects.equals(getUser(), favorites.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFavId(), getImdbId(), getUser());
    }

    @Override
    public String toString() {
        return "Favorites{" +
                "favId=" + favId +
                ", imdbId='" + imdbId + '\'' +
                ", user=" + user +
                '}';
    }
}
