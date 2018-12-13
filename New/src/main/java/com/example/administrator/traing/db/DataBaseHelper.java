package com.example.administrator.traing.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.orhanobut.logger.Logger;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/1.
 */

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {


    private static final String DATABASE_NAME = "NewsDB.db";
    private static final int DATABASE_VERSION = 1;
    private Map<String, Dao> daoMap = new HashMap<>();

    private DataBaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    //单例模式
    private static DataBaseHelper instance;

    static synchronized DataBaseHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (DataBaseHelper.class) {

                if (instance == null) {

                    instance = new DataBaseHelper(context);

                }
            }
        }

        return instance;
    }


    /**
     * 获得数据的访问对象
     *
     * @param clazz
     * @return
     */

    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daoMap.containsKey(className)) {

            dao = daoMap.get(className);

        } else {

            dao = super.getDao(clazz);
            daoMap.put(className, dao);

        }

        return dao;
    }


    /**
     * close database
     */

    public void close() {

        super.close();
        for (String key : daoMap.keySet()) {
            Dao dao = daoMap.get(key);
            dao = null;

        }

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        try {

            TableUtils.createTable(connectionSource, NewsHistory.class);
            TableUtils.createTable(connectionSource, NotLoginCollect.class);
            TableUtils.createTable(connectionSource, NewsSub.class);
            TableUtils.createTable(connectionSource, AccountCache.class);
            TableUtils.createTable(connectionSource, Account.class);
            TableUtils.createTable(connectionSource, AllSiteInfo.class);
            TableUtils.createTable(connectionSource, NotLoginSub.class);
            TableUtils.createTable(connectionSource, AccountSub.class);
            TableUtils.createTable(connectionSource, LoginSiteSub.class);
            TableUtils.createTable(connectionSource, LoginCollect.class);


        } catch (SQLException e) {
            e.printStackTrace();

            Logger.d("创建失败");

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

        try {

            TableUtils.dropTable(connectionSource, NewsHistory.class, true);
            TableUtils.dropTable(connectionSource, NotLoginCollect.class, true);
            TableUtils.dropTable(connectionSource, NewsSub.class, true);
            TableUtils.dropTable(connectionSource, AccountCache.class, true);
            TableUtils.dropTable(connectionSource, Account.class, true);
            TableUtils.dropTable(connectionSource, AllSiteInfo.class, true);
            TableUtils.dropTable(connectionSource, NotLoginSub.class, true);
            TableUtils.dropTable(connectionSource, AccountSub.class, true);
            TableUtils.dropTable(connectionSource, LoginSiteSub.class, true);
            TableUtils.dropTable(connectionSource, LoginCollect.class, true);

        } catch (SQLException e) {
            e.printStackTrace();

            Logger.d("数据库删除失败");

        }

    }
}
