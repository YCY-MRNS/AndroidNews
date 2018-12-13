package com.example.administrator.traing.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/9/20.
 */

public class LoginSiteDao {


    private Context context;
    private Dao<LoginSiteSub, Integer> sites;


    public LoginSiteDao(Context context) {
        this.context = context;
        DataBaseHelper helper = DataBaseHelper.getInstance(context);
        try {
            sites = helper.getDao(LoginSiteSub.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(LoginSiteSub allSiteInfo) {

        try {

            sites.create(allSiteInfo);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public List<LoginSiteSub> queryAll() {

        List<LoginSiteSub> subCaches = null;
        try {

            subCaches = sites.queryBuilder().selectColumns("site_id", "type", "des", "pic", "site").distinct().query();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subCaches;
    }

    public List<LoginSiteSub> queryByAccount(String name) {

        List<LoginSiteSub> subCaches = null;
        try {

            subCaches = sites.queryBuilder().selectColumns("site_id", "type", "des", "pic", "site").distinct().where().eq("name", name).query();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subCaches;
    }


    public boolean whereAdd(String s, String name) {

        try {

            if (sites.queryBuilder().where().eq("site", s).and().eq("name", name).query() == null) {

                return false;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return true;
    }


    public List<LoginSiteSub> queryById(String siteId) {

        List<LoginSiteSub> subCaches = null;

        try {

            subCaches = sites.queryBuilder().where().eq("ids", siteId).query();

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return subCaches;

    }


    public void deleteBySiteId(String site) {

        try {

            DeleteBuilder<LoginSiteSub, Integer> deleteBuilder = sites.deleteBuilder();
            deleteBuilder.where().eq("site_id", site);
            deleteBuilder.prepare();
            deleteBuilder.delete();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    public void deleteBySiteId(String site, String name) {

        try {

            DeleteBuilder<LoginSiteSub, Integer> deleteBuilder = sites.deleteBuilder();
            deleteBuilder.where().eq("site_id", site).and().eq("name", name);
            deleteBuilder.prepare();
            deleteBuilder.delete();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }


}
