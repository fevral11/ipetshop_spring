package ru.ognivenko.ipetshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ognivenko.ipetshop.bean.Goods;
import ru.ognivenko.ipetshop.bean.OrderInfo;
import ru.ognivenko.ipetshop.bean.User;
import ru.ognivenko.ipetshop.dao.DaoException;
import ru.ognivenko.ipetshop.dao.OrderDao;
import ru.ognivenko.ipetshop.service.OrderService;
import ru.ognivenko.ipetshop.service.ServiceException;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    @Transactional
    public void addGoodsToOrder(Integer goodsId, Integer orderId) throws ServiceException {
        try {
            orderDao.addGoodsToOrder(goodsId, orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public void deleteGoodsInOrder(Integer orderId, Integer goodsId) throws ServiceException {
        try {
            orderDao.deleteGoodsInOrder(orderId, goodsId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public List<OrderInfo> getParticularGoodsFromOrder(Integer orderId, Integer goodsId) throws ServiceException {
        try {
            List<OrderInfo> orderInfoList = orderDao.getParticularGoodsFromOrder(orderId, goodsId);
            return orderInfoList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public List<Goods> getGoodsFromOrder(Integer orderId) throws ServiceException {
        try {
            List<Goods> goodsList = orderDao.getGoodsFromOrder(orderId);
            return goodsList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public Integer createBasket(User user) throws ServiceException {
        try {
            Integer userId = user.getUserId();
            Integer orderId = orderDao.createBasket(userId);
            return orderId;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
