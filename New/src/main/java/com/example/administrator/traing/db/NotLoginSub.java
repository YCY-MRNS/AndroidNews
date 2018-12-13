package com.example.administrator.traing.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2017/9/16.
 */

@DatabaseTable(tableName = "not_login_site_Sub")
public class NotLoginSub {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "site")
    private String name;

    @DatabaseField(columnName = "type")
    private String type;

    @DatabaseField(columnName = "des")
    private String description;

    @DatabaseField(columnName = "pic")
    private String pic;

    @DatabaseField(columnName = "site_id")
    private String site_id;

    public NotLoginSub() {
    }

    public NotLoginSub(int id, String name, String type, String description, String pic) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.pic = pic;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }


    @Override
    public String toString() {
        return "AllSiteInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }
}
