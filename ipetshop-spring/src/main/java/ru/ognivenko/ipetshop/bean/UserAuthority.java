package ru.ognivenko.ipetshop.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "authorization_data")
public class UserAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idauthorization_data")
    private Integer authorityId;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "userAuthority", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private User user;

    public UserAuthority() {
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        UserAuthority that = (UserAuthority) o;
        return Objects.equals(authorityId, that.authorityId) &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorityId, login, password, user);
    }

    @Override
    public String toString() {
        return "UserAuthority{" +
                "authorityId=" + authorityId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user +
                '}';
    }
}
