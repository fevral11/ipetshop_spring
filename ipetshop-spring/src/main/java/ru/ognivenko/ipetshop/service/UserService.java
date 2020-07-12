package ru.ognivenko.ipetshop.service;

import ru.ognivenko.ipetshop.bean.User;
import ru.ognivenko.ipetshop.bean.UserAuthority;
import ru.ognivenko.ipetshop.dao.DaoException;

public interface UserService {

    void addUser(User user) throws ServiceException;

    void deleteUser(User user) throws ServiceException;

    User getUser(Integer userId) throws ServiceException;

    User authorization(UserAuthority userAuthority) throws ServiceException;
}
