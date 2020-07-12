package ru.ognivenko.ipetshop.dao;

import ru.ognivenko.ipetshop.bean.Goods;

import java.util.List;


public interface GoodsDao {

    List<Goods> getListGoods() throws DaoException;

}
