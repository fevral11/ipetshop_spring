package ru.ognivenko.ipetshop.service;

import ru.ognivenko.ipetshop.bean.Goods;

import java.util.List;

public interface GoodsService {

    List<Goods> getListGoods() throws ServiceException;

}
