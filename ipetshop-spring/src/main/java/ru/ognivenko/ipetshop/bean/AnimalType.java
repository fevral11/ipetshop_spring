package ru.ognivenko.ipetshop.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "catalog")
public class AnimalType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcatalog")
    private Integer animalTypeId;

    @Column(name = "type_animal")
    private String animalType;

    @OneToMany(mappedBy = "animalType",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<GoodsType> goodsTypes;

    public AnimalType() {
    }

    public Integer getAnimalTypeId() {
        return animalTypeId;
    }

    public void setAnimalTypeId(Integer animalTypeId) {
        this.animalTypeId = animalTypeId;
    }

    public List<GoodsType> getGoodsTypes() {
        return goodsTypes;
    }

    public void setGoodsTypes(List<GoodsType> goodsTypes) {
        this.goodsTypes = goodsTypes;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalType that = (AnimalType) o;
        return Objects.equals(animalTypeId, that.animalTypeId) &&
                Objects.equals(animalType, that.animalType) &&
                Objects.equals(goodsTypes, that.goodsTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalTypeId, animalType, goodsTypes);
    }

    @Override
    public String toString() {
        return "AnimalType{" +
                "animalTypeId=" + animalTypeId +
                ", animalType='" + animalType + '\'' +
                '}';
    }
}
