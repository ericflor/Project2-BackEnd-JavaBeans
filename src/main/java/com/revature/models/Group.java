package com.revature.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "group_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private boolean open;
    //@OneToMany // do we need to map/associate users in a group? //nah, use userDAO.findByGroup to get all users in a group
    // private User user;
    //private List<String> movies;
//    @OneToMany(mappedBy = "user.group")
//    @JsonManagedReference
//    private List<Decisions> decisions;

//    public Group() {
//    }
//
//    public Group(int id, String name, boolean open) {
//        this.id = id;
//        this.name = name;
//        this.open = open;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public boolean isOpen() {
//        return open;
//    }
//
//    public void setOpen(boolean open) {
//        this.open = open;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Group)) return false;
//        Group group = (Group) o;
//        return getId() == group.getId() && isOpen() == group.isOpen() && Objects.equals(getName(), group.getName());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getName(), isOpen());
//    }
//
//    @Override
//    public String toString() {
//        return "Group{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", open=" + open +
//                '}';
//    }
}
