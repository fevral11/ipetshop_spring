package ru.ognivenko.ipetshop.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.ognivenko.ipetshop.bean.User;
import ru.ognivenko.ipetshop.bean.UserAuthority;
import ru.ognivenko.ipetshop.dao.DaoException;
import ru.ognivenko.ipetshop.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

    private static final String USER_STATUS_DELETE = "delete";
    private static final String PARAMETER_QUERY_LOGIN = "login";
    private static final String PARAMETER_QUERY_PASSWORD = "password";
    private static final String PARAMETER_QUERY_USER_ID = "userId";
    private static final String QUERY_GET_USER = "from User where id=:userId";
    private static final String QUERY_GET_USER_LOGIN = "from UserAuthority where login=:login and password=:password";

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) throws DaoException {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(user.getUserAuthority());
        currentSession.saveOrUpdate(user);
    }

    @Override
    public void deleteUser(User user) throws DaoException {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(user);
    }

    @Override
    public User getUser(Integer userId) throws DaoException {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<User> queryGetUser = currentSession.createQuery(QUERY_GET_USER, User.class);
        queryGetUser.setParameter(PARAMETER_QUERY_USER_ID, userId);
        User user = queryGetUser.getSingleResult();
        return user;
    }

    @Override
    public User authorization(UserAuthority userAuthority) throws DaoException {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<UserAuthority> queryGetUser = currentSession.createQuery(QUERY_GET_USER_LOGIN, UserAuthority.class);
        queryGetUser.setParameter(PARAMETER_QUERY_LOGIN, userAuthority.getLogin());
        queryGetUser.setParameter(PARAMETER_QUERY_PASSWORD, userAuthority.getPassword());
        UserAuthority userAuthorities = queryGetUser.getSingleResult();
        User user = getUser(userAuthorities.getUser().getUserId());
        if (USER_STATUS_DELETE.equals(user.getStatusUser())) {
            return null;
        } else {
            return user;
        }
    }
}
