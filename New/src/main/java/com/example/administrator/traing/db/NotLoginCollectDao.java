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

public class NotLoginCollectDao {

    private Context context;

    private Dao<NotLoginCollect, Integer> newsCollectDao;

    public NotLoginCollectDao(Context context) {
        this.context = context;

        try {

            DataBaseHelper helper = DataBaseHelper.getInstance(context);
            newsCollectDao = helper.getDao(NotLoginCollect.class);

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.d("getDao fail");
        }

    }

    public void addMyCollect(NotLoginCollect collect) {

        try {

            newsCollectDao.create(collect);

        } catch (SQLException e) {
            e.printStackTrace();

            Logger.d("dao create fail " + e);

        }

    }


    public List<NotLoginCollect> queryByDistinctTitle() {

        List<NotLoginCollect> list = null;

        try {

            list = newsCollectDao.queryBuilder().selectColumns("title", "from", "url", "img").distinct().query();

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return list;
    }

    public int deleteByTitle(String s) {

        try {

            DeleteBuilder<NotLoginCollect, Integer> deleteBuilder = newsCollectDao.deleteBuilder();
            deleteBuilder.where().eq("title", s);

            return deleteBuilder.delete();

        } catch (SQLException e) {

            e.printStackTrace();

        }
        return 0;
    }

    public int queryListSizeByTitle(String s) {

        List<NotLoginCollect> list = null;
        try {

            list = newsCollectDao.queryBuilder().where().eq("title", s).query();
            return list.size();

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return 0;
    }

}
