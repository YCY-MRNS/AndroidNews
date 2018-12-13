package com.example.administrator.traing.domain;

/**
 * Created by Administrator on 2017/9/16.
 */

public class SiteInfo {

    private int id;

    private String name;

    private String type;

    private String description;

    private String pic;

    private String site_id;

    public SiteInfo() {
    }

    public SiteInfo(int id, String name, String type, String description, String pic) {
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
