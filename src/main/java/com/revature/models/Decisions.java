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
    private String title;
    private boolean choice;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

//    @ManyToOne // maybe use this if we can't figure out query property expressions
//    private Group group;


    public Decisions() {
    }

    public Decisions(int decisionsId, int roundId, String imdbId, String title, boolean choice, User user) {
        this.decisionsId = decisionsId;
        this.roundId = roundId;
        this.imdbId = imdbId;
        this.title = title;
        this.choice = choice;
        this.user = user;
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

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChoice() {
        return choice;
    }

    public void setChoice(boolean choice) {
        this.choice = choice;
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
        if (!(o instanceof Decisions)) return false;
        Decisions decisions = (Decisions) o;
        return getDecisionsId() == decisions.getDecisionsId() && getRoundId() == decisions.getRoundId() && isChoice() == decisions.isChoice() && Objects.equals(getImdbId(), decisions.getImdbId()) && Objects.equals(getTitle(), decisions.getTitle()) && Objects.equals(getUser(), decisions.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDecisionsId(), getRoundId(), getImdbId(), getTitle(), isChoice(), getUser());
    }

    @Override
    public String toString() {
        return "Decisions{" +
                "decisionsId=" + decisionsId +
                ", roundId=" + roundId +
                ", imdbId='" + imdbId + '\'' +
                ", title='" + title + '\'' +
                ", choice=" + choice +
                ", user=" + user +
                '}';
    }
}
