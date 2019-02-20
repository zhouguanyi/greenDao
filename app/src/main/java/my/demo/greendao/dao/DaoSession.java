package my.demo.greendao.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import my.demo.greendao.entity.User;
import my.demo.greendao.entity.Person;

import my.demo.greendao.dao.UserDao;
import my.demo.greendao.dao.PersonDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig personDaoConfig;

    private final UserDao userDao;
    private final PersonDao personDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        personDaoConfig = daoConfigMap.get(PersonDao.class).clone();
        personDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        personDao = new PersonDao(personDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(Person.class, personDao);
    }
    
    public void clear() {
        userDaoConfig.clearIdentityScope();
        personDaoConfig.clearIdentityScope();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

}