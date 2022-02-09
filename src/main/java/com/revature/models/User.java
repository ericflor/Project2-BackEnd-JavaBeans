package com.revature.models;

import java.io.Serializable;
import java.util.Objects;

public class User {

    public enum  UserRole {
        MEMBER, ADMIN
    }

    // enum.values() is expensive in performance, so we cache the values once across instances
    private static final UserRole[] UserRoleValues = UserRole.values();

    private int id;
    private String username;
    private String password;
    private String email;
    private int groupId;
    private int roleId;
    private UserRole role;

    public User() {}

    public User(int id, String username, String password, String email, int groupId, int roleId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.groupId = groupId;
        this.roleId = roleId;
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && groupId == user.groupId && roleId == user.roleId && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, groupId, roleId, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", groupId=" + groupId +
                ", roleId=" + roleId +
                ", role=" + role +
                '}';
    }
}
