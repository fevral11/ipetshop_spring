package ru.ognivenko.ipetshop.dao;

import ru.ognivenko.ipetshop.bean.Goods;
import ru.ognivenko.ipetshop.bean.OrderInfo;

import java.util.List;

public interface OrderDao {

    void addGoodsToOrder(Integer goodsId, Integer orderId) throws DaoException;

    void deleteGoodsInOrder(Integer orderId, Integer goodsId) throws DaoException;

    List<OrderInfo> getParticularGoodsFromOrder(Integer orderId, Integer goodsId) throws DaoException;

    List<Goods> getGoodsFromOrder(Integer orderId) throws DaoException;

    Integer createBasket(Integer userId) throws DaoException;
}
