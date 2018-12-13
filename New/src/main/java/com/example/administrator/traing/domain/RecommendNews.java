package com.example.administrator.traing.domain;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2017/8/22.
 */

@Table(name = "RecommendNews")
public class RecommendNews {

    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "newsTitle")
    private String newsTitle;   //新闻标题

    @Column(name = "newsUrl")
    private String newsUrl;     //新闻链接地址

    @Column(name = "newsTime")
    private String newsTime;    //新闻时间与来源

    @Column(name = "newsImg")
    private String newsImg;     //新闻img

    @Column(name = "newsImg2")
    private String newsImg2;    //新闻img2

    @Column(name = "newsImg3")
    private String newsImg3;    //新闻img3

    @Column(name = "newsDesc")
    private String newsDesc;    //新闻概要

    @Column(name = "newsLogo")
    private String newsLogo;    //新闻概要

    public RecommendNews() {

    }

    public RecommendNews(String newsTitle, String newsUrl, String newsTime, String newsImg, String newsDesc, String newsLogo) {
        this.newsTitle = newsTitle;
        this.newsUrl = newsUrl;
        this.newsTime = newsTime;
        this.newsImg = newsImg;
        this.newsDesc = newsDesc;
        this.newsLogo = newsLogo;
    }

    public RecommendNews(String newsTitle, String newsUrl, String newsTime, String newsImg, String newsImg2, String newsImg3, String newsDesc, String newsLogo) {
        this.newsTitle = newsTitle;
        this.newsUrl = newsUrl;
        this.newsTime = newsTime;
        this.newsImg = newsImg;
        this.newsImg2 = newsImg2;
        this.newsImg3 = newsImg3;
        this.newsDesc = newsDesc;
        this.newsLogo = newsLogo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

    public String getNewsImg() {
        return newsImg;
    }

    public void setNewsImg(String newsImg) {
        this.newsImg = newsImg;
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

    public String getNewsDesc() {
        return newsDesc;
    }

    public void setNewsDesc(String newsDesc) {
        this.newsDesc = newsDesc;
    }

    public String getNewsLogo() {
        return newsLogo;
    }

    public void setNewsLogo(String newsLogo) {
        this.newsLogo = newsLogo;
    }


    @Override
    public String toString() {
        return "RecommendNews{" +
                "id=" + id +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsUrl='" + newsUrl + '\'' +
                ", newsTime='" + newsTime + '\'' +
                ", newsImg='" + newsImg + '\'' +
                ", newsImg2='" + newsImg2 + '\'' +
                ", newsImg3='" + newsImg3 + '\'' +
                ", newsDesc='" + newsDesc + '\'' +
                ", newsLogo='" + newsLogo + '\'' +
                '}';
    }
}
