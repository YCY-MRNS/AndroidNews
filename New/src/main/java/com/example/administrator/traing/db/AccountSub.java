package com.example.administrator.traing.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2017/9/16.
 */

@DatabaseTable(tableName = "account_ids")
public class AccountSub {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "name")
    private String name;

    @DatabaseField(columnName = "site_id")
    private String site_id;

    public AccountSub() {
    }

    public AccountSub(int id, String name, String site_id) {
        this.id = id;
        this.name = name;
        this.site_id = site_id;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
