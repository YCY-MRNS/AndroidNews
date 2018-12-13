package com.example.administrator.traing.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2017/9/1.
 */

@DatabaseTable(tableName = "not_login_collect")
public class NotLoginCollect {

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

    public NotLoginCollect(int id, String img, String title, String from, String url) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.from = from;
        this.url = url;
    }

    public NotLoginCollect() {
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
