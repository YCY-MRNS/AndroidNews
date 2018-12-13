package com.example.administrator.traing.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.orhanobut.logger.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */

public class NewsHistoryDao {
    private Context context;
    private Dao<NewsHistory, Integer> newsHistoryDao;
    private DataBaseHelper helper;

    public NewsHistoryDao(Context context) {

        this.context = context;

        try {

            helper = DataBaseHelper.getInstance(context);
            newsHistoryDao = helper.getDao(NewsHistory.class);

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.d("getDao fail");
        }

    }

    public void addNewsHistory(NewsHistory history) {

        try {

            newsHistoryDao.create(history);

        } catch (SQLException e) {
            e.printStackTrace();

            Logger.d("dao create fail " + e);

        }

    }

    public void update(NewsHistory history) {
        try {
            newsHistoryDao.update(history);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void delete(NewsHistory history) {
        try {

            newsHistoryDao.delete(history);

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    public void deleteByList(List<NewsHistory> historyList) {

        for (NewsHistory history : historyList) {
            delete(history);
        }

    }

    public void deleteById(List<Integer> ids) {

        try {
            newsHistoryDao.deleteIds(ids);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<NewsHistory> listAll() {

        try {
            return newsHistoryDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
