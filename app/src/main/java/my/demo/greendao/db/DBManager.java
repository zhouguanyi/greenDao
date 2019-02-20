package my.demo.greendao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import my.demo.greendao.dao.DaoMaster;
import my.demo.greendao.dao.DaoSession;
import my.demo.greendao.dao.UserDao;
import my.demo.greendao.entity.User;


public class DBManager extends Exception{
    UserDao userDao;
    public DBManager(Context context) {
        DaoMaster.DevOpenHelper  helper = new DaoMaster.DevOpenHelper(context, "JackWaiting-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
    }

    public ArrayList<User> getDatasByType(int type){
        return (ArrayList<User>) userDao.queryBuilder().where(UserDao.Properties.Name.eq(type)).list();
    }

    public User getDataByUserId(long userId){
        return userDao.load(userId);//根据主键查
    }

    public ArrayList<User> getDataByUserAge(long age){
        return (ArrayList<User>) userDao.queryBuilder().where(UserDao.Properties.Age.eq(age)).list();//根据Age查
    }

    public List<User> getUserAll(){
        return  userDao.loadAll();
    }

    public void insertUser(User user) {
        userDao.insert(user);
    }

    public void deleteUser(User user){
        userDao.delete(user);
    }

    public void deleteUserById(Long userId){
        userDao.deleteByKey(userId);
    }

    public void deleteAll(){
        userDao.deleteAll();
    }

    public void updateUser(User user){

        userDao.update(user);
    }


}
