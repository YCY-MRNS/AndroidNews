package com.example.administrator.traing.domain;

import java.util.List;

/**
 * Created by Administrator on 2017/8/26.
 */

public class SubBanner {

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
        private List<BannerBean> banner;
        private List<SiteBean> site;
        private List<CateBean> cate;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<SiteBean> getSite() {
            return site;
        }

        public void setSite(List<SiteBean> site) {
            this.site = site;
        }

        public List<CateBean> getCate() {
            return cate;
        }

        public void setCate(List<CateBean> cate) {
            this.cate = cate;
        }

        public static class BannerBean {
            /**
             * id : 1790
             * name : 设计癖
             * keyword :
             * url :
             * pic : http://image.s-reader.com/rss/site_pic/1790.png
             * brief : 发现好设计
             * cid : ,11,
             * article_num : 1860
             * order_num : 25678
             * source : 3
             * status : 1
             * create_time : 1461730001
             * update_time : 1503740735
             * rss_url : http://www.shejipi.com/feed
             * pk : 0
             * wx_biz :
             * rss_update_time : 1503739203
             * last_rss_time : 1503739203
             * last_pub_time : 1503674441
             * is_multi_weixin : 0
             * is_only_from_weixin : 0
             * sort : 2000
             * is_recommend : 1
             * admin_uid : 1
             * is_grab : 1
             * is_only_original : 0
             * rss_type : 0
             * online_time : 0
             * is_findrecom : 1
             * banner : http://image.s-reader.com/setting/rss/ae23ced7ae2366bd9942310a3f2ce781.png
             * is_sub : 0
             * cate_info : [{"id":"11","name":"生活","icon":"http://res.smartisanos.cn/setting/rss/e575bf1bccbc924ccb94f485a4c24f41.png","brief":"","status":"1","sort":"6","create_time":"1449221354","update_time":"1480909948","site_ids":"599,600,617,630,636,638,643,685,870,1097,1337,1345,1459,1611,1676,1689,1738,1783,1784,1785,1790,1792,1794,1795,1801,1814,1822,1837,1839,1840,1843,648,1844","recom_ids":""}]
             */

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
            private String banner;
            private int is_sub;
            private List<CateInfoBean> cate_info;

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

            public String getBanner() {
                return banner;
            }

            public void setBanner(String banner) {
                this.banner = banner;
            }

            public int getIs_sub() {
                return is_sub;
            }

            public void setIs_sub(int is_sub) {
                this.is_sub = is_sub;
            }

            public List<CateInfoBean> getCate_info() {
                return cate_info;
            }

            public void setCate_info(List<CateInfoBean> cate_info) {
                this.cate_info = cate_info;
            }

            public static class CateInfoBean {
                /**
                 * id : 11
                 * name : 生活
                 * icon : http://res.smartisanos.cn/setting/rss/e575bf1bccbc924ccb94f485a4c24f41.png
                 * brief :
                 * status : 1
                 * sort : 6
                 * create_time : 1449221354
                 * update_time : 1480909948
                 * site_ids : 599,600,617,630,636,638,643,685,870,1097,1337,1345,1459,1611,1676,1689,1738,1783,1784,1785,1790,1792,1794,1795,1801,1814,1822,1837,1839,1840,1843,648,1844
                 * recom_ids :
                 */

                private String id;
                private String name;
                private String icon;
                private String brief;
                private String status;
                private String sort;
                private String create_time;
                private String update_time;
                private String site_ids;
                private String recom_ids;

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

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getSort() {
                    return sort;
                }

                public void setSort(String sort) {
                    this.sort = sort;
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

                public String getSite_ids() {
                    return site_ids;
                }

                public void setSite_ids(String site_ids) {
                    this.site_ids = site_ids;
                }

                public String getRecom_ids() {
                    return recom_ids;
                }

                public void setRecom_ids(String recom_ids) {
                    this.recom_ids = recom_ids;
                }
            }
        }

        public static class SiteBean {
            /**
             * id : 1854
             * name : 骚客文艺
             * keyword :
             * url :
             * pic : http://image.s-reader.com/rss/site_pic/1854.png
             * brief : 有趣的灵魂终将会相遇
             * cid : ,10,
             * article_num : 33
             * order_num : 422
             * source : 2
             * status : 1
             * create_time : 1503308719
             * update_time : 1503740962
             * rss_url : soulker2017
             * pk : 0
             * wx_biz : MzUzOTA3NzE2MA==
             * rss_update_time : 1503763199
             * last_rss_time : 1503659163
             * last_pub_time : 1503719711
             * is_multi_weixin : 0
             * is_only_from_weixin : 0
             * sort : 2000
             * is_recommend : 1
             * admin_uid : 1
             * is_grab : 1
             * is_only_original : 0
             * rss_type : 0
             * online_time : 1503381983
             * is_findrecom : 1
             * is_sub : 0
             * cate_info : [{"id":"10","name":"文艺","icon":"http://res.smartisanos.cn/setting/rss/23c313ca1c11587b881dffd6d665cf99.png","brief":"","status":"1","sort":"3","create_time":"1449220909","update_time":"1480909591","site_ids":"590,614,621,631,640,641,645,647,652,681,965,1038,1131,1275,1286,1320,1334,1370,1426,1486,1552,1780,1789,1803,1808,1815,1817,1823,1828","recom_ids":""}]
             * is_new : 0
             */

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
            private int is_sub;
            private int is_new;
            private List<CateInfoBeanX> cate_info;

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

            public int getIs_sub() {
                return is_sub;
            }

            public void setIs_sub(int is_sub) {
                this.is_sub = is_sub;
            }

            public int getIs_new() {
                return is_new;
            }

            public void setIs_new(int is_new) {
                this.is_new = is_new;
            }

            public List<CateInfoBeanX> getCate_info() {
                return cate_info;
            }

            public void setCate_info(List<CateInfoBeanX> cate_info) {
                this.cate_info = cate_info;
            }

            public static class CateInfoBeanX {
                /**
                 * id : 10
                 * name : 文艺
                 * icon : http://res.smartisanos.cn/setting/rss/23c313ca1c11587b881dffd6d665cf99.png
                 * brief :
                 * status : 1
                 * sort : 3
                 * create_time : 1449220909
                 * update_time : 1480909591
                 * site_ids : 590,614,621,631,640,641,645,647,652,681,965,1038,1131,1275,1286,1320,1334,1370,1426,1486,1552,1780,1789,1803,1808,1815,1817,1823,1828
                 * recom_ids :
                 */

                private String id;
                private String name;
                private String icon;
                private String brief;
                private String status;
                private String sort;
                private String create_time;
                private String update_time;
                private String site_ids;
                private String recom_ids;

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

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getBrief() {
                    return brief;
                }

                public void setBrief(String brief) {
                    this.brief = brief;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getSort() {
                    return sort;
                }

                public void setSort(String sort) {
                    this.sort = sort;
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

                public String getSite_ids() {
                    return site_ids;
                }

                public void setSite_ids(String site_ids) {
                    this.site_ids = site_ids;
                }

                public String getRecom_ids() {
                    return recom_ids;
                }

                public void setRecom_ids(String recom_ids) {
                    this.recom_ids = recom_ids;
                }
            }
        }

        public static class CateBean {
            /**
             * id : 16
             * name : 新闻
             * icon : http://res.smartisanos.cn/setting/rss/c24de8848fcd9d54afd8eb6dc6c51a72.png
             * brief :
             * status : 1
             * sort : 1
             * create_time : 1449221526
             * update_time : 1480909260
             * site_ids : 616,627,650,1782,1793,1798,1800,1807,1810,1830,1832
             * recom_ids :
             */

            private String id;
            private String name;
            private String icon;
            private String brief;
            private String status;
            private String sort;
            private String create_time;
            private String update_time;
            private String site_ids;
            private String recom_ids;

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

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
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

            public String getSite_ids() {
                return site_ids;
            }

            public void setSite_ids(String site_ids) {
                this.site_ids = site_ids;
            }

            public String getRecom_ids() {
                return recom_ids;
            }

            public void setRecom_ids(String recom_ids) {
                this.recom_ids = recom_ids;
            }
        }
    }
}
