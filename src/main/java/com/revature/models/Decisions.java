package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Decisions {

    private int roundId;
    @ManyToOne
    private User user;
    @ManyToOne
    private Group group;
    private String imdbId;
    private boolean choice;

    public Decisions() {
    }

    public Decisions(int roundId, User user, Group group, String imdbId, boolean choice) {
        this.roundId = roundId;
        this.user = user;
        this.group = group;
        this.imdbId = imdbId;
        this.choice = choice;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
        return roundId == decisions.roundId && choice == decisions.choice && Objects.equals(user, decisions.user) && Objects.equals(group, decisions.group) && Objects.equals(imdbId, decisions.imdbId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundId, user, group, imdbId, choice);
    }

    @Override
    public String toString() {
        return "Decisions{" +
                "roundId=" + roundId +
                ", user=" + user +
                ", group=" + group +
                ", imdbId='" + imdbId + '\'' +
                ", choice=" + choice +
                '}';
    }
}
