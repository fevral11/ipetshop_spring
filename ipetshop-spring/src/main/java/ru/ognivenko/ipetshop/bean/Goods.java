package ru.ognivenko.ipetshop.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgoods")
    private Integer goodsId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "subdirectory_idsubdirectory")
    private GoodsType goodsType;

    @Column(name = "title")
    private String goodsTitle;

    @Column(name = "manufacturer")
    private String goodsManufacturer;

    @Column(name = "price")
    private Double goodsPrice;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "special_offer_idspecial_offer")
    private SpecialOffer specialOffer;

    @OneToMany(mappedBy = "goods", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<OrderInfo> orderInfoList;

    @Column(name = "price_special_offer")
    private Double priceSpecialOffer;

    @Column(name = "picture")
    private String goodsPicture;

    @Column(name = "description")
    private String goodsDescription;

    @Column(name = "quantity_warehouse")
    private Integer quantityOnWarehouse;

    @Column(name = "status")
    private String goodsStatus;

    public Goods() {
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getGoodsManufacturer() {
        return goodsManufacturer;
    }

    public void setGoodsManufacturer(String goodsManufacturer) {
        this.goodsManufacturer = goodsManufacturer;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Double getPriceSpecialOffer() {
        return priceSpecialOffer;
    }

    public void setPriceSpecialOffer(Double priceSpecialOffer) {
        this.priceSpecialOffer = priceSpecialOffer;
    }

    public String getGoodsPicture() {
        return goodsPicture;
    }

    public void setGoodsPicture(String goodsPicture) {
        this.goodsPicture = goodsPicture;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public Integer getQuantityOnWarehouse() {
        return quantityOnWarehouse;
    }

    public void setQuantityOnWarehouse(Integer quantityOnWarehouse) {
        this.quantityOnWarehouse = quantityOnWarehouse;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public SpecialOffer getSpecialOffer() {
        return specialOffer;
    }

    public void setSpecialOffer(SpecialOffer specialOffer) {
        this.specialOffer = specialOffer;
    }

    public List<OrderInfo> getOrderInfoList() {
        return orderInfoList;
    }

    public void setOrderInfoList(List<OrderInfo> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }

    public void addOrderInfo(OrderInfo orderInfo) {
        if (orderInfoList == null) {
            orderInfoList = new ArrayList<>();
        }
        orderInfoList.add(orderInfo);
        orderInfo.setGoods(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Objects.equals(goodsId, goods.goodsId) &&
                Objects.equals(goodsType, goods.goodsType) &&
                Objects.equals(goodsTitle, goods.goodsTitle) &&
                Objects.equals(goodsManufacturer, goods.goodsManufacturer) &&
                Objects.equals(goodsPrice, goods.goodsPrice) &&
                Objects.equals(specialOffer, goods.specialOffer) &&
                Objects.equals(orderInfoList, goods.orderInfoList) &&
                Objects.equals(priceSpecialOffer, goods.priceSpecialOffer) &&
                Objects.equals(goodsPicture, goods.goodsPicture) &&
                Objects.equals(goodsDescription, goods.goodsDescription) &&
                Objects.equals(quantityOnWarehouse, goods.quantityOnWarehouse) &&
                Objects.equals(goodsStatus, goods.goodsStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsId, goodsType, goodsTitle, goodsManufacturer, goodsPrice, specialOffer, orderInfoList, priceSpecialOffer, goodsPicture, goodsDescription, quantityOnWarehouse, goodsStatus);
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", goodsType=" + goodsType +
                ", goodsTitle='" + goodsTitle + '\'' +
                ", goodsManufacturer='" + goodsManufacturer + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", specialOffer=" + specialOffer +
                ", orderInfoList=" + orderInfoList +
                ", priceSpecialOffer=" + priceSpecialOffer +
                ", goodsPicture='" + goodsPicture + '\'' +
                ", goodsDescription='" + goodsDescription + '\'' +
                ", quantityOnWarehouse=" + quantityOnWarehouse +
                ", goodsStatus='" + goodsStatus + '\'' +
                '}';
    }
}

