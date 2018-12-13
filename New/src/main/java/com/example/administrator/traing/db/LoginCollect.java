package com.example.administrator.traing.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2017/9/1.
 */

@DatabaseTable(tableName = "login_collect")
public class LoginCollect {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "img")
    private String img;

    @DatabaseField(columnName = "title", canBeNull = false)
    private String title;

    @DatabaseField(columnName = "from")
    private String from;

    @DatabaseField(columnName = "url")
    private String url;

    @DatabaseField(columnName = "name")
    private String name;

    public LoginCollect(int id, String img, String title, String from, String url, String name) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.from = from;
        this.url = url;
        this.name = name;
    }

    public LoginCollect() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NewsHistory{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", title='" + title + '\'' +
                ", from='" + from + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
