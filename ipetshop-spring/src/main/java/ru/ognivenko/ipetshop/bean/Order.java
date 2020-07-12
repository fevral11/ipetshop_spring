package ru.ognivenko.ipetshop.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorder")
    private Integer orderId;

    @Column(name = "date_order")
    private Timestamp orderDate;

    @Column(name = "status")
    private String orderStatus;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private List<OrderInfo> orderInfoList;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "users_idusers")
    private User user;

    public Order() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderInfo> getOrderInfoList() {
        return orderInfoList;
    }

    public void setOrderInfoList(List<OrderInfo> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addOrderInfo(OrderInfo orderInfo) {
        if (orderInfoList == null) {
            orderInfoList = new ArrayList<>();
        }
        orderInfoList.add(orderInfo);
        orderInfo.setOrder(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(orderStatus, order.orderStatus) &&
                Objects.equals(orderInfoList, order.orderInfoList) &&
                Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderDate, orderStatus, orderInfoList, user);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderInfoList=" + orderInfoList +
                ", user=" + user +
                '}';
    }
}
