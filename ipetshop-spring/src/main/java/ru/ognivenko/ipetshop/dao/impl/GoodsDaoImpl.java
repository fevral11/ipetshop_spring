package ru.ognivenko.ipetshop.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.ognivenko.ipetshop.bean.Goods;
import ru.ognivenko.ipetshop.dao.DaoException;
import ru.ognivenko.ipetshop.dao.GoodsDao;

import java.util.List;

@Repository
public class GoodsDaoImpl implements GoodsDao {

    private static final String QUERY_GET_GOODS = "from Goods";

    private final SessionFactory sessionFactory;

    @Autowired
    public GoodsDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Goods> getListGoods() throws DaoException {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Goods> queryGetGoods = currentSession.createQuery(QUERY_GET_GOODS, Goods.class);
        return queryGetGoods.getResultList();
    }
}