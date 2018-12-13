package com.example.administrator.traing.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */

public class AccountSubDao {

    private Context context;
    private Dao<AccountSub, Integer> dao;

    public AccountSubDao(Context context) {
        this.context = context;
        DataBaseHelper helper = DataBaseHelper.getInstance(context);
        try {
            dao = helper.getDao(AccountSub.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean whereAdd(String site) {

        try {

            if (dao.queryBuilder().where().eq("site_id", site).query() == null) {

                return false;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return true;
    }

    public boolean whereAdd(String site, String name) {

        try {

            if (dao.queryBuilder().where().eq("site_id", site).and().eq("name", name).query() == null) {

                return false;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return true;
    }

    public void addMyAccount(AccountSub sub) {

        try {
            dao.createIfNotExists(sub);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AccountSub> querySubSite(String site) {
        List<AccountSub> accountSubs = null;

        try {
            accountSubs = dao.queryBuilder().where().eq("site_id", site).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return accountSubs;
    }

    public List<AccountSub> querySubSite(String site, String name) {
        List<AccountSub> accountSubs = null;

        try {
            accountSubs = dao.queryBuilder().where().eq("name", name).and().eq("site_id", site).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return accountSubs;
    }


    public List<AccountSub> querySubsDistinct(String name) {
        List<AccountSub> accountSubs = null;

        try {
            accountSubs = dao.queryBuilder().selectColumns("site_id").where().eq("name", name).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return accountSubs;
    }


    public List<AccountSub> querySub(String name) {

        List<AccountSub> accountSubs = null;

        try {

            accountSubs = dao.queryBuilder().selectColumns("site_id").distinct().where().eq("name", name).query();

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return accountSubs;
    }

    public void deleteBySiteId(String site) {

        try {

            DeleteBuilder<AccountSub, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().eq("site_id", site);
            deleteBuilder.prepare();
            deleteBuilder.delete();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }


    public void deleteBySiteId(String site, String name) {

        try {

            DeleteBuilder<AccountSub, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().eq("name", name).and().eq("site_id", site);
            deleteBuilder.delete();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }
}
