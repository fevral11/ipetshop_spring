package ru.ognivenko.ipetshop.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusers")
    private Integer userId;

    @Column(name = "name")
    private String nameUser;

    @Column(name = "surname")
    private String surnameUser;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "status")
    private String statusUser;


    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "authorization_data_idauthorization_data")
    private UserAuthority userAuthority;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "authorities_idauthorities")
    private UserRole userRole;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Order> orderList;

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getSurnameUser() {
        return surnameUser;
    }

    public void setSurnameUser(String surnameUser) {
        this.surnameUser = surnameUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(String statusUser) {
        this.statusUser = statusUser;
    }

    public UserAuthority getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(UserAuthority userAuthority) {
        this.userAuthority = userAuthority;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void addOrders(Order order) {
        if (orderList == null) {
            orderList = new ArrayList<>();
        }
        orderList.add(order);
        order.setUser(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(nameUser, user.nameUser) &&
                Objects.equals(surnameUser, user.surnameUser) &&
                Objects.equals(email, user.email) &&
                Objects.equals(telephone, user.telephone) &&
                Objects.equals(statusUser, user.statusUser) &&
                Objects.equals(userAuthority, user.userAuthority) &&
                Objects.equals(userRole, user.userRole) &&
                Objects.equals(orderList, user.orderList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, nameUser, surnameUser, email, telephone,
                statusUser, userAuthority, userRole, orderList);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", nameUser='" + nameUser + '\'' +
                ", surnameUser='" + surnameUser + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", statusUser='" + statusUser + '\'' +
                ", userAuthority=" + userAuthority +
                ", userRole=" + userRole +
                ", orderList=" + orderList +
                '}';
    }
}
