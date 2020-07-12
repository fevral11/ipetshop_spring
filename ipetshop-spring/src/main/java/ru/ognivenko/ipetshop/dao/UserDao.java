package ru.ognivenko.ipetshop.dao;

import ru.ognivenko.ipetshop.bean.User;
import ru.ognivenko.ipetshop.bean.UserAuthority;

public interface UserDao {

    void addUser(User user) throws DaoException;

    void deleteUser(User user) throws DaoException;

    User getUser(Integer userId) throws DaoException;

    User authorization(UserAuthority userAuthority) throws DaoException;

}
