package com.example.administrator.traing.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/9/20.
 */

public class AllSiteDao {


    private Context context;
    private Dao<AllSiteInfo, Integer> sites;
    private DataBaseHelper helper;


    public AllSiteDao(Context context) {
        this.context = context;
        helper = DataBaseHelper.getInstance(context);
        try {
            sites = helper.getDao(AllSiteInfo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(AllSiteInfo allSiteInfo) {

        try {

            sites.create(allSiteInfo);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean whereAdd(String s) {

        try {

            if (sites.queryBuilder().where().eq("site", s).query() == null) {

                return false;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return true;
    }

    public List<AllSiteInfo> queryById(String siteId) {

        List<AllSiteInfo> subCaches = null;

        try {

            subCaches = sites.queryBuilder().where().eq("ids", siteId).query();

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return subCaches;

    }


}
