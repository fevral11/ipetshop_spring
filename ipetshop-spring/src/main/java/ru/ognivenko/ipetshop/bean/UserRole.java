package ru.ognivenko.ipetshop.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "authorities")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idauthorities")
    private Integer roleId;

    @Column(name = "authoritiy")
    private String role;

    @OneToMany(mappedBy = "userRole", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private List<User> users;

    public UserRole() {
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(roleId, userRole.roleId) &&
                Objects.equals(role, userRole.role) &&
                Objects.equals(users, userRole.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, role, users);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "roleId=" + roleId +
                ", role='" + role + '\'' +
                ", users=" + users +
                '}';
    }
}
