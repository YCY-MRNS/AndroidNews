package com.example.administrator.traing.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.orhanobut.logger.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */

public class AccountCacheDao {

    private Context context;
    private Dao<AccountCache, Integer> accountIntegerDao;
    private DataBaseHelper helper;

    public AccountCacheDao(Context context) {
        this.context = context;
        helper = DataBaseHelper.getInstance(context);
        try {
            accountIntegerDao = helper.getDao(AccountCache.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public List<AccountCache> querySubSite() {

        try {

            return accountIntegerDao.queryBuilder().selectColumns("name").distinct().query();

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.d(e);
        }

        return null;
    }


    public List<AccountCache> queryId(String s) {
        List<AccountCache> list = null;

        try {

            list = accountIntegerDao.queryBuilder().where().eq("name", s).query();

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.d(e);
        }

        return list;
    }


    public void addMyAccount(AccountCache accountCache) {

        try {
            accountIntegerDao.create(accountCache);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int deleteByName(String id) {

        try {

            DeleteBuilder<AccountCache, Integer> deleteBuilder = accountIntegerDao.deleteBuilder();
            deleteBuilder.where().eq("name", id);

            return deleteBuilder.delete();

        } catch (SQLException e) {

            e.printStackTrace();

        }
        return 0;
    }
}
