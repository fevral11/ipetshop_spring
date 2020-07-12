package ru.ognivenko.ipetshop.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "order_info")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorder_info")
    private Integer orderInfoId;

    @Column(name = "quantity")
    private Integer quantityGoodsInOrder;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "goods_idgoods")
    private Goods goods;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "order_idorder")
    private Order order;

    public OrderInfo() {
    }

    public Integer getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(Integer orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public Integer getQuantityGoodsInOrder() {
        return quantityGoodsInOrder;
    }

    public void setQuantityGoodsInOrder(Integer quantityGoodsInOrder) {
        this.quantityGoodsInOrder = quantityGoodsInOrder;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderInfo orderInfo = (OrderInfo) o;
        return Objects.equals(orderInfoId, orderInfo.orderInfoId) &&
                Objects.equals(quantityGoodsInOrder, orderInfo.quantityGoodsInOrder) &&
                Objects.equals(goods, orderInfo.goods) &&
                Objects.equals(order, orderInfo.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderInfoId, quantityGoodsInOrder, goods, order);
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderInfoId=" + orderInfoId +
                ", quantityGoodsInOrder=" + quantityGoodsInOrder +
                ", goods=" + goods +
                ", order=" + order +
                '}';
    }
}
