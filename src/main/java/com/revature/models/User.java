package com.revature.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;


import javax.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user_tbl")
public class User {

    public enum  UserRole {
        MEMBER, ADMIN
    }

    // enum.values() is expensive in performance, so we cache the values once across instances
    private static final UserRole[] UserRoleValues = UserRole.values();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    @ManyToOne(fetch = FetchType.EAGER)
    private Group group;
    private int roleId;
    @Transient
    private UserRole role;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Favorites> favs;

    public User() {}

    public User(int id, String username, String password, String email, Group group, int roleId, List<Favorites> favs) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.group = group;
        this.roleId = roleId;
        this.favs = favs;
        this.role = UserRoleValues[roleId - 1];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
        this.role = UserRoleValues[roleId - 1];
    }

    public UserRole getRole() {
        return role;
    }

    public List<Favorites> getFavs() {
        return favs;
    }

    public void setFavs(List<Favorites> favs) {
        this.favs = favs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && roleId == user.roleId && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(group, user.group) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, group, roleId, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", group=" + group +
                ", roleId=" + roleId +
                ", role=" + role +
                '}';
    }
}
