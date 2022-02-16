package com.revature.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Decisions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int decisionsId;
    private int roundId;
    private String imdbId;
    private boolean choice;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

//    @ManyToOne // maybe use this if we can't figure out query property expressions
//    private Group group;


    public Decisions() {
    }

    public Decisions(int decisionsId, int roundId, User user, String imdbId, boolean choice) {
        this.decisionsId = decisionsId;
        this.roundId = roundId;
        this.user = user;
        this.imdbId = imdbId;
        this.choice = choice;
    }

    public int getDecisionsId() {
        return decisionsId;
    }

    public void setDecisionsId(int decisionsId) {
        this.decisionsId = decisionsId;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public boolean isChoice() {
        return choice;
    }

    public void setChoice(boolean choice) {
        this.choice = choice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Decisions decisions = (Decisions) o;
        return getDecisionsId() == decisions.getDecisionsId() && getRoundId() == decisions.getRoundId() && isChoice() == decisions.isChoice() && Objects.equals(getUser(), decisions.getUser()) && Objects.equals(getImdbId(), decisions.getImdbId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDecisionsId(), getRoundId(), getUser(), getImdbId(), isChoice());
    }

    @Override
    public String toString() {
        return "Decisions{" +
                "decisionsId=" + decisionsId +
                ", roundId=" + roundId +
                ", user=" + user +
                ", imdbId='" + imdbId + '\'' +
                ", choice=" + choice +
                '}';
    }
}
