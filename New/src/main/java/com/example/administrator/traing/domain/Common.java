package com.example.administrator.traing.domain;

import java.util.List;

/**
 * Created by Administrator on 2017/8/23.
 */

public class Common {

    private int code;
    private DataBean data;
    private List<?> errInfo;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public List<?> getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(List<?> errInfo) {
        this.errInfo = errInfo;
    }

    public static class DataBean {

        private int count;
        private int page_count;
        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPage_count() {
            return page_count;
        }

        public void setPage_count(int page_count) {
            this.page_count = page_count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {


            private String id;
            private String site_id;
            private String title;
            private String headpic;
            private String author_id;
            private String author_name;
            private String brief;
            private String read_num;
            private String collect_num;
            private String origin_url;
            private String url;
            private String status;
            private String create_time;
            private String update_time;
            private String pub_date;
            private String md5;
            private String is_recommend;
            private String prepic1;
            private String prepic2;
            private String prepic3;
            private String recommend_time;
            private String sort_score;
            private SiteInfoBean site_info;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSite_id() {
                return site_id;
            }

            public void setSite_id(String site_id) {
                this.site_id = site_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getHeadpic() {
                return headpic;
            }

            public void setHeadpic(String headpic) {
                this.headpic = headpic;
            }

            public String getAuthor_id() {
                return author_id;
            }

            public void setAuthor_id(String author_id) {
                this.author_id = author_id;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getRead_num() {
                return read_num;
            }

            public void setRead_num(String read_num) {
                this.read_num = read_num;
            }

            public String getCollect_num() {
                return collect_num;
            }

            public void setCollect_num(String collect_num) {
                this.collect_num = collect_num;
            }

            public String getOrigin_url() {
                return origin_url;
            }

            public void setOrigin_url(String origin_url) {
                this.origin_url = origin_url;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getPub_date() {
                return pub_date;
            }

            public void setPub_date(String pub_date) {
                this.pub_date = pub_date;
            }

            public String getMd5() {
                return md5;
            }

            public void setMd5(String md5) {
                this.md5 = md5;
            }

            public String getIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(String is_recommend) {
                this.is_recommend = is_recommend;
            }

            public String getPrepic1() {
                return prepic1;
            }

            public void setPrepic1(String prepic1) {
                this.prepic1 = prepic1;
            }

            public String getPrepic2() {
                return prepic2;
            }

            public void setPrepic2(String prepic2) {
                this.prepic2 = prepic2;
            }

            public String getPrepic3() {
                return prepic3;
            }

            public void setPrepic3(String prepic3) {
                this.prepic3 = prepic3;
            }

            public String getRecommend_time() {
                return recommend_time;
            }

            public void setRecommend_time(String recommend_time) {
                this.recommend_time = recommend_time;
            }

            public String getSort_score() {
                return sort_score;
            }

            public void setSort_score(String sort_score) {
                this.sort_score = sort_score;
            }

            public SiteInfoBean getSite_info() {
                return site_info;
            }

            public void setSite_info(SiteInfoBean site_info) {
                this.site_info = site_info;
            }

            public static class SiteInfoBean {


                private String id;
                private String name;
                private String keyword;
                private String url;
                private String pic;
                private String brief;
                private String cid;
                private String article_num;
                private String order_num;
                private String source;
                private String status;
                private String create_time;
                private String update_time;
                private String rss_url;
                private String pk;
                private String wx_biz;
                private String rss_update_time;
                private String last_rss_time;
                private String last_pub_time;
                private String is_multi_weixin;
                private String is_only_from_weixin;
                private String sort;
                private String is_recommend;
                private String admin_uid;
                private String is_grab;
                private String is_only_original;
                private String rss_type;
                private String online_time;
                private String is_findrecom;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getKeyword() {
                    return keyword;
                }

                public void setKeyword(String keyword) {
                    this.keyword = keyword;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getCid() {
                    return cid;
                }

                public void setCid(String cid) {
                    this.cid = cid;
                }

                public String getArticle_num() {
                    return article_num;
                }

                public void setArticle_num(String article_num) {
                    this.article_num = article_num;
                }

                public String getOrder_num() {
                    return order_num;
                }

                public void setOrder_num(String order_num) {
                    this.order_num = order_num;
                }

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }

                public String getUpdate_time() {
                    return update_time;
                }

                public void setUpdate_time(String update_time) {
                    this.update_time = update_time;
                }

                public String getRss_url() {
                    return rss_url;
                }

                public void setRss_url(String rss_url) {
                    this.rss_url = rss_url;
                }

                public String getPk() {
                    return pk;
                }

                public void setPk(String pk) {
                    this.pk = pk;
                }

                public String getWx_biz() {
                    return wx_biz;
                }

                public void setWx_biz(String wx_biz) {
                    this.wx_biz = wx_biz;
                }

                public String getRss_update_time() {
                    return rss_update_time;
                }

                public void setRss_update_time(String rss_update_time) {
                    this.rss_update_time = rss_update_time;
                }

                public String getLast_rss_time() {
                    return last_rss_time;
                }

                public void setLast_rss_time(String last_rss_time) {
                    this.last_rss_time = last_rss_time;
                }

                public String getLast_pub_time() {
                    return last_pub_time;
                }

                public void setLast_pub_time(String last_pub_time) {
                    this.last_pub_time = last_pub_time;
                }

                public String getIs_multi_weixin() {
                    return is_multi_weixin;
                }

                public void setIs_multi_weixin(String is_multi_weixin) {
                    this.is_multi_weixin = is_multi_weixin;
                }

                public String getIs_only_from_weixin() {
                    return is_only_from_weixin;
                }

                public void setIs_only_from_weixin(String is_only_from_weixin) {
                    this.is_only_from_weixin = is_only_from_weixin;
                }

                public String getSort() {
                    return sort;
                }

                public void setSort(String sort) {
                    this.sort = sort;
                }

                public String getIs_recommend() {
                    return is_recommend;
                }

                public void setIs_recommend(String is_recommend) {
                    this.is_recommend = is_recommend;
                }

                public String getAdmin_uid() {
                    return admin_uid;
                }

                public void setAdmin_uid(String admin_uid) {
                    this.admin_uid = admin_uid;
                }

                public String getIs_grab() {
                    return is_grab;
                }

                public void setIs_grab(String is_grab) {
                    this.is_grab = is_grab;
                }

                public String getIs_only_original() {
                    return is_only_original;
                }

                public void setIs_only_original(String is_only_original) {
                    this.is_only_original = is_only_original;
                }

                public String getRss_type() {
                    return rss_type;
                }

                public void setRss_type(String rss_type) {
                    this.rss_type = rss_type;
                }

                public String getOnline_time() {
                    return online_time;
                }

                public void setOnline_time(String online_time) {
                    this.online_time = online_time;
                }

                public String getIs_findrecom() {
                    return is_findrecom;
                }

                public void setIs_findrecom(String is_findrecom) {
                    this.is_findrecom = is_findrecom;
                }
            }
        }
    }
}
