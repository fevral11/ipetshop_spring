package ru.ognivenko.ipetshop.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.ognivenko.ipetshop.bean.Goods;
import ru.ognivenko.ipetshop.bean.Order;
import ru.ognivenko.ipetshop.bean.OrderInfo;
import ru.ognivenko.ipetshop.bean.User;
import ru.ognivenko.ipetshop.dao.DaoException;
import ru.ognivenko.ipetshop.dao.OrderDao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    private static final String QUERY_GET_PARTICULAR_GOODS_FROM_ORDER = "from OrderInfo where goods.goodsId" +
            "=:goodsId and order.orderId=:orderId";
    private static final String QUERY_GET_GOODS_FROM_ORDER = "from OrderInfo where order.orderId=:orderId";
    private static final String PARAMETER_QUERY_GOODS_ID = "goodsId";
    private static final String PARAMETER_QUERY_ORDER_ID = "orderId";
    private static final String ORDER_STATUS_CREATE = "created";

    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer createBasket(Integer userId) throws DaoException {
        Session currentSession = sessionFactory.getCurrentSession();
        User user=currentSession.get(User.class,userId);
        Order order = new Order();
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setOrderStatus(ORDER_STATUS_CREATE);
        user.addOrders(order);
        currentSession.save(order);
        currentSession.persist(order);
        return order.getOrderId();
    }

    @Override
    public void addGoodsToOrder(Integer goodsId, Integer orderId) throws DaoException {
        Session currentSession = sessionFactory.getCurrentSession();
        Order order = currentSession.get(Order.class, orderId);
        Goods goods = currentSession.get(Goods.class, goodsId);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setQuantityGoodsInOrder(1);
        goods.addOrderInfo(orderInfo);
        order.addOrderInfo(orderInfo);
        currentSession.save(orderInfo);
    }

    @Override
    public void deleteGoodsInOrder(Integer orderId, Integer goodsId) throws DaoException {
        Session currentSession = sessionFactory.getCurrentSession();
        List<OrderInfo> orderInfoList = getParticularGoodsFromOrder(orderId, goodsId);
        OrderInfo orderInfo = orderInfoList.get(0);
        currentSession.delete(orderInfo);
    }

    @Override
    public List<OrderInfo> getParticularGoodsFromOrder(Integer orderId, Integer goodsId) throws DaoException {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<OrderInfo> queryGetGoodsFromOrder = currentSession.createQuery(QUERY_GET_PARTICULAR_GOODS_FROM_ORDER,
                OrderInfo.class);
        queryGetGoodsFromOrder.setParameter(PARAMETER_QUERY_GOODS_ID, goodsId);
        queryGetGoodsFromOrder.setParameter(PARAMETER_QUERY_ORDER_ID, orderId);
        List<OrderInfo> orderInfoList = queryGetGoodsFromOrder.getResultList();
        return orderInfoList;
    }

    @Override
    public List<Goods> getGoodsFromOrder(Integer orderId) throws DaoException {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<OrderInfo> queryGetGoodsFromOrder = currentSession.createQuery(QUERY_GET_GOODS_FROM_ORDER, OrderInfo.class);
        queryGetGoodsFromOrder.setParameter(PARAMETER_QUERY_ORDER_ID, orderId);
        List<OrderInfo> orderInfoList = queryGetGoodsFromOrder.getResultList();
        List<Goods> goodsList = new ArrayList<>();
        for (OrderInfo orderInfo : orderInfoList) {
            goodsList.add(orderInfo.getGoods());
        }
        return goodsList;
    }
}
