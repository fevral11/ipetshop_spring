package ru.ognivenko.ipetshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ognivenko.ipetshop.bean.User;
import ru.ognivenko.ipetshop.bean.UserAuthority;
import ru.ognivenko.ipetshop.dao.DaoException;
import ru.ognivenko.ipetshop.dao.UserDao;
import ru.ognivenko.ipetshop.service.ServiceException;
import ru.ognivenko.ipetshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_STATUS_DELETE = "delete";

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void addUser(User user) throws ServiceException{
        try {
            userDao.addUser(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public void deleteUser(User user) throws ServiceException{
        try {
            user.setStatusUser(USER_STATUS_DELETE);
            userDao.deleteUser(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public User getUser(Integer userId) throws ServiceException{
        try {
            return userDao.getUser(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public User authorization(UserAuthority userAuthority) throws ServiceException{
        try {
            return userDao.authorization(userAuthority);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
