package com.example.administrator.traing.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2017/9/13.
 */

@DatabaseTable(tableName = "sub")
public class NewsSub {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "site")
    private String site;

    public NewsSub() {

    }

    public NewsSub(int id, String site) {
        this.id = id;
        this.site = site;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {

        this.site = site;
    }

    @Override
    public String toString() {
        return "NewsSub{" +
                "id=" + id +
                ", site='" + site + '\'' +
                '}';
    }
}
