package ru.ognivenko.ipetshop.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "special_offer")
public class SpecialOffer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idspecial_offer")
    private Integer specialOfferId;

    @Column(name = "type")
    private String typeSale;

    @Column(name = "value")
    private String valueSale;

    @Column(name = "date_start")
    private Time dateStart;

    @Column(name = "date_end")
    private Time dateEnd;

    @OneToMany(mappedBy = "specialOffer", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Goods> goods;

    public SpecialOffer() {
    }

    public Integer getSpecialOfferId() {
        return specialOfferId;
    }

    public void setSpecialOfferId(Integer specialOfferId) {
        this.specialOfferId = specialOfferId;
    }

    public String getTypeSale() {
        return typeSale;
    }

    public void setTypeSale(String typeSale) {
        this.typeSale = typeSale;
    }

    public String getValueSale() {
        return valueSale;
    }

    public void setValueSale(String valueSale) {
        this.valueSale = valueSale;
    }

    public Time getDateStart() {
        return dateStart;
    }

    public void setDateStart(Time dateStart) {
        this.dateStart = dateStart;
    }

    public Time getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Time dateEnd) {
        this.dateEnd = dateEnd;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goodsList) {
        this.goods = goodsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialOffer that = (SpecialOffer) o;
        return Objects.equals(specialOfferId, that.specialOfferId) &&
                Objects.equals(typeSale, that.typeSale) &&
                Objects.equals(valueSale, that.valueSale) &&
                Objects.equals(dateStart, that.dateStart) &&
                Objects.equals(dateEnd, that.dateEnd) &&
                Objects.equals(goods, that.goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialOfferId, typeSale, valueSale, dateStart, dateEnd, goods);
    }

    @Override
    public String toString() {
        return "SpecialOffer{" +
                "specialOfferId=" + specialOfferId +
                ", typeSale='" + typeSale + '\'' +
                ", valueSale='" + valueSale + '\'' +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                '}';
    }
}
