package com.example.administrator.traing.domain;

/**
 * Created by Administrator on 2017/7/17.
 */

public class NewsBean {

    private String newsTitle;   //新闻标题
    private String newsUrl;     //新闻链接地址
    private String newsTime;    //新闻时间与来源
    private String newsImg;     //新闻img
    private String newsImg2;    //新闻img2
    private String newsImg3;    //新闻img3
    private String newsDesc;    //新闻概要

    public NewsBean(String newsTitle, String newsUrl, String newsTime, String newsImg, String newsDesc) {
        this.newsTitle = newsTitle;
        this.newsUrl = newsUrl;
        this.newsTime = newsTime;
        this.newsImg = newsImg;
        this.newsDesc = newsDesc;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "newsTitle='" + newsTitle + '\'' +
                ", newsUrl='" + newsUrl + '\'' +
                ", newsTime='" + newsTime + '\'' +
                ", newsImg='" + newsImg + '\'' +
                ", newsImg2='" + newsImg2 + '\'' +
                ", newsImg3='" + newsImg3 + '\'' +
                ", newsDesc='" + newsDesc + '\'' +
                '}';
    }

    public String getNewsImg2() {
        return newsImg2;
    }

    public void setNewsImg2(String newsImg2) {
        this.newsImg2 = newsImg2;
    }

    public String getNewsImg3() {
        return newsImg3;
    }

    public void setNewsImg3(String newsImg3) {
        this.newsImg3 = newsImg3;
    }

    public String getNewsImg() {
        return newsImg;
    }

    public void setNewsImg(String newsImg) {
        this.newsImg = newsImg;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getNewsDesc() {
        return newsDesc;
    }

    public void setNewsDesc(String newsDesc) {
        this.newsDesc = newsDesc;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

}
