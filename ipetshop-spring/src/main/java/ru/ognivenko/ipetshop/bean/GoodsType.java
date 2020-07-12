package ru.ognivenko.ipetshop.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "subdirectory")
public class GoodsType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsubdirectory")
    private Integer typeGoodsID;

    @Column(name = "type_goods")
    private String goodsType;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "catalog_idcatalog")
    private AnimalType animalType;

    @OneToMany(mappedBy = "goodsType", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Goods> goods;


    public GoodsType() {
    }

    public Integer getTypeGoodsID() {
        return typeGoodsID;
    }

    public void setTypeGoodsID(Integer typeGoodsID) {
        this.typeGoodsID = typeGoodsID;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsType goodsType1 = (GoodsType) o;
        return Objects.equals(typeGoodsID, goodsType1.typeGoodsID) &&
                Objects.equals(goodsType, goodsType1.goodsType) &&
                Objects.equals(animalType, goodsType1.animalType) &&
                Objects.equals(goods, goodsType1.goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeGoodsID, goodsType, animalType, goods);
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "typeGoodsID=" + typeGoodsID +
                ", goodsType='" + goodsType + '\'' +
                ", animalType=" + animalType +
                '}';
    }
}


