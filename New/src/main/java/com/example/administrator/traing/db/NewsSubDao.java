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

public class NewsSubDao {

    private Context context;
    private Dao<NewsSub, Integer> newsSubsDao;
    private DataBaseHelper helper;

    public NewsSubDao(Context context) {
        this.context = context;
        helper = DataBaseHelper.getInstance(context);
        try {
            newsSubsDao = helper.getDao(NewsSub.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<NewsSub> querySubSite() {

        try {

            return newsSubsDao.queryBuilder().selectColumns("site").distinct().query();

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.d(e);
        }

        return null;
    }

    public List<NewsSub> querySubSite(String num) {

        try {

            return newsSubsDao.queryBuilder().where().eq("site", num).query();

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.d(e);
        }

        return null;

    }

    public void addMySub(NewsSub sub) {

        try {
            newsSubsDao.create(sub);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean whereAdd(String name) {

        try {

            if (newsSubsDao.queryBuilder().where().eq("site", name).query() == null) {

                return false;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return true;
    }

    public int deleteByTitle(String id) {

        try {

            DeleteBuilder<NewsSub, Integer> deleteBuilder = newsSubsDao.deleteBuilder();
            deleteBuilder.where().eq("site", id);

            return deleteBuilder.delete();

        } catch (SQLException e) {

            e.printStackTrace();

        }
        return 0;
    }

}
