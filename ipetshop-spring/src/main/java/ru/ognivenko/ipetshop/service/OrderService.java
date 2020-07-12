package ru.ognivenko.ipetshop.service;

import ru.ognivenko.ipetshop.bean.Goods;
import ru.ognivenko.ipetshop.bean.Order;
import ru.ognivenko.ipetshop.bean.OrderInfo;
import ru.ognivenko.ipetshop.bean.User;
import ru.ognivenko.ipetshop.dao.DaoException;

import java.util.List;

public interface OrderService {

    void addGoodsToOrder(Integer goodsId, Integer orderId) throws ServiceException;

    void deleteGoodsInOrder(Integer orderId, Integer goodsId) throws ServiceException;

    List<OrderInfo> getParticularGoodsFromOrder(Integer orderId, Integer goodsId) throws ServiceException;

    List<Goods> getGoodsFromOrder(Integer orderId) throws ServiceException;

    Integer createBasket(User user) throws ServiceException;
}
