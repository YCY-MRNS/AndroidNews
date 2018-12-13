package com.example.administrator.traing.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.orhanobut.logger.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */

public class AccountDao {

    private Context context;
    private Dao<Account, Integer> accountIntegerDao;
    private DataBaseHelper helper;

    public AccountDao(Context context) {
        this.context = context;
        helper = DataBaseHelper.getInstance(context);
        try {
            accountIntegerDao = helper.getDao(Account.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public List<Account> queryId(String s) {

        List<Account> list = null;
        try {

            list = accountIntegerDao.queryBuilder().where().eq("name", s).query();

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.d(e);
        }

        return list;
    }


    public void addMyAccount(Account accountCache) {

        try {
            accountIntegerDao.create(accountCache);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void update(int id, String name, String pw, String description, String pic) {

        try {

            accountIntegerDao.update(new Account(id, name, pw, description, pic));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
