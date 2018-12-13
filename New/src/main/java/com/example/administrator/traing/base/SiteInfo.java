package com.example.administrator.traing.base;

import java.util.List;

/**
 * Created by Administrator on 2017/9/21.
 */

public class SiteInfo {

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
        /**
         * name : 功夫财经
         * keyword :
         * url :
         * pic : http://image.s-reader.com/rss/site_pic/1841.png
         * brief : 联动财经的力量
         * cid : ,34,
         * article_num : 1092
         * order_num : 8156
         * source : 2
         * status : 1
         * create_time : 1474943455
         * update_time : 1505924149
         * rss_url : kongfuf
         * pk : 0
         * rss_update_time : 1505923199
         * sort : 2000
         * is_recommend : 1
         * admin_uid : 1
         * is_grab : 1
         * id : 1841
         * wx_biz : MzIzOTA3NTA5Mg==
         * last_rss_time : 1505779322
         * last_pub_time : 1505860227
         * is_multi_weixin : 0
         * is_only_from_weixin : 0
         * is_only_original : 0
         * rss_type : 0
         * online_time : 1475034610
         * is_findrecom : 0
         * cate_info : [{"id":"34","name":"商业","icon":"http://res.smartisanos.cn/setting/rss/6c6398c3359982e2976c2b42f5eeef13.png","brief":"","status":"1","sort":"4","create_time":"1450779594","update_time":"1496891577","site_ids":"585,592,610,615,629,635,646,1336,1714,1788,1812,1816,1829,1834,1838,1841,1845,1847,1848,1851","recom_ids":""}]
         * is_new : 0
         * is_sub : 0
         */

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
        private String rss_update_time;
        private String sort;
        private String is_recommend;
        private String admin_uid;
        private String is_grab;
        private String id;
        private String wx_biz;
        private String last_rss_time;
        private String last_pub_time;
        private String is_multi_weixin;
        private String is_only_from_weixin;
        private String is_only_original;
        private String rss_type;
        private String online_time;
        private String is_findrecom;
        private int is_new;
        private int is_sub;
        private List<CateInfoBean> cate_info;

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

        public String getRss_update_time() {
            return rss_update_time;
        }

        public void setRss_update_time(String rss_update_time) {
            this.rss_update_time = rss_update_time;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWx_biz() {
            return wx_biz;
        }

        public void setWx_biz(String wx_biz) {
            this.wx_biz = wx_biz;
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

        public int getIs_new() {
            return is_new;
        }

        public void setIs_new(int is_new) {
            this.is_new = is_new;
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
             * id : 34
             * name : 商业
             * icon : http://res.smartisanos.cn/setting/rss/6c6398c3359982e2976c2b42f5eeef13.png
             * brief :
             * status : 1
             * sort : 4
             * create_time : 1450779594
             * update_time : 1496891577
             * site_ids : 585,592,610,615,629,635,646,1336,1714,1788,1812,1816,1829,1834,1838,1841,1845,1847,1848,1851
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
