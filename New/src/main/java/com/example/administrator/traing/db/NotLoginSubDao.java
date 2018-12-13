package com.example.administrator.traing.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/9/20.
 */

public class NotLoginSubDao {


    private Context context;
    private Dao<NotLoginSub, Integer> sites;
    private DataBaseHelper helper;

    public NotLoginSubDao(Context context) {
        this.context = context;
        helper = DataBaseHelper.getInstance(context);
        try {
            sites = helper.getDao(NotLoginSub.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(NotLoginSub site) {

        try {

            sites.create(site);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public List<NotLoginSub> queryAll() {

        List<NotLoginSub> subCaches = null;
        try {

            subCaches = sites.queryBuilder().selectColumns("site_id", "type", "des", "pic", "site").distinct().query();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subCaches;
    }

    public void deleteByName(String site) {

        try {

            DeleteBuilder<NotLoginSub, Integer> deleteBuilder = sites.deleteBuilder();
            deleteBuilder.where().eq("site", site);
            deleteBuilder.prepare();
            deleteBuilder.delete();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    public boolean orNotAdd(String site) {

        try {

            if (sites.queryBuilder().where().eq("site", site).query() == null) {

                return false;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return true;
    }

}
