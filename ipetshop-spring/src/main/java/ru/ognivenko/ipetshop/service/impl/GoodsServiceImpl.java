package ru.ognivenko.ipetshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ognivenko.ipetshop.bean.Goods;
import ru.ognivenko.ipetshop.dao.DaoException;
import ru.ognivenko.ipetshop.dao.GoodsDao;
import ru.ognivenko.ipetshop.service.GoodsService;
import ru.ognivenko.ipetshop.service.ServiceException;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    private GoodsDao goodsDAO;

    @Autowired
    public GoodsServiceImpl(GoodsDao goodsDAO) throws ServiceException {
        this.goodsDAO = goodsDAO;
    }

    @Override
    @Transactional
    public List<Goods> getListGoods() throws ServiceException {
        try {
            List<Goods> goodsList = goodsDAO.getListGoods();
            return goodsList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}