package com.example.administrator.traing.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.orhanobut.logger.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */

public class LoginCollectDao {

    private Context context;

    private Dao<LoginCollect, Integer> newsCollectDao;

    public LoginCollectDao(Context context) {
        this.context = context;

        try {

            DataBaseHelper helper = DataBaseHelper.getInstance(context);
            newsCollectDao = helper.getDao(LoginCollect.class);

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.d("getDao fail");
        }

    }

    public void addMyCollect(LoginCollect collect) {

        try {

            newsCollectDao.create(collect);

        } catch (SQLException e) {
            e.printStackTrace();

            Logger.d("dao create fail " + e);

        }

    }


    public List<LoginCollect> listAll() {

        try {
            return newsCollectDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LoginCollect> queryByDistinctTitle() {

        List<LoginCollect> list = null;

        try {

            list = newsCollectDao.queryBuilder().selectColumns("title", "from", "url", "img").distinct().query();

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return list;
    }

    public int deleteByTitle(String s, String name) {

        try {

            DeleteBuilder<LoginCollect, Integer> deleteBuilder = newsCollectDao.deleteBuilder();
            deleteBuilder.where().eq("title", s).and().eq("name", name);

            return deleteBuilder.delete();

        } catch (SQLException e) {

            e.printStackTrace();

        }
        return 0;
    }

    public boolean whereAdd(String title) {

        try {

            if (newsCollectDao.queryBuilder().where().eq("title", title).query() == null) {

                return false;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean whereAdd(String title, String name) {

        try {

            if (newsCollectDao.queryBuilder().where().eq("title", title).and().eq("name", name).query() == null) {

                return false;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }


    public List<LoginCollect> queryByName(String s) {

        List<LoginCollect> loginCollects = null;

        try {

            loginCollects = newsCollectDao.queryBuilder().where().eq("name", s).query();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loginCollects;
    }


    public boolean queryListSizeByjTitle(String s, String name) {

        try {
            if (newsCollectDao.queryBuilder().where().eq("title", s).and().eq("name", name).query().size() > 0) {

                return true;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
