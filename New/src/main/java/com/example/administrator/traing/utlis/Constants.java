package com.example.administrator.traing.utlis;

/**
 * Created by Administrator on 2017/7/13.
 */

public class Constants {

    /**
     * 天气
     */

    public static final String NEWS_HOME_W = "http://apicloud.mob.com/v1/weather/query?key=appkey&city=";

    public static final String WEATHER_BASE = "http://apicloud.mob.com/";

    /**
     * 每日一图
     * bing.com
     * http://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=3
     */

    public static final String NEWS_HOME_PIC = "http://cn.bing.com/HPImageArchive.aspx?format=js&idx=1&n=1";

    public static final String NEWS_HOME_PIC_BASIC = "http://cn.bing.com";

    /**
     * <p>

     * <p>
     * LIST
     * http://reader.smartisan.com/index.php?r=article/getList&site_id=1854&offset=0&page_size=20
     * 生活
     * http://reader.smartisan.com/index.php?r=find/GetArticleList&cate_id=11&art_id=&page_size=20
     * <p>
     * 科技
     * http://reader.smartisan.com/index.php?r=find/GetArticleList&cate_id=15&art_id=&page_size=20
     * <p>
     * 文艺
     * http://reader.smartisan.com/index.php?r=find/GetArticleList&cate_id=45&art_id=&page_size=20
     * <p>
     * 商业
     * http://reader.smartisan.com/index.php?r=find/GetArticleList&cate_id=34&art_id=&page_size=20
     * <p>
     * 非虚构
     * http://reader.smartisan.com/index.php?r=find/GetArticleList&cate_id=44&art_id=&page_size=20
     * <p>
     * 科学
     * http://reader.smartisan.com/index.php?r=find/GetArticleList&cate_id=43&art_id=&page_size=20
     * <p>
     * 精选
     * http://reader.smartisan.com/index.php?r=find/GetArticleList&cate_id=999&art_id=&page_size=20
     * <p>
     * 主页
     * http://reader.smartisan.com/index.php?r=line/show&offset=0&page_size=20
     * <p>
     * 订阅
     * http://reader.smartisan.com/index.php?r=myCenter/show&site_ids=1
     * <p>
     * 列表
     * http://reader.smartisan.com/index.php?r=find/getInterestList
     * <p>
     * 新闻
     * <p>
     * http://reader.smartisan.com/index.php?r=find/GetArticleList&cate_id=16&art_id=&page_size=20
     * <p>
     */

    public static final String NEWS_HOME_RECOMMEND = "http://reader.smartisan.com/index.php?r=line/show&offset=";

    public static final String NEWS_HOME_HOT_NEWS = "http://reader.smartisan.com/index.php?r=article/getList&site_id=1810&offset=0&page_size=6";

    public static final String NEWS_EXPLORE_VIDEO = "http://reader.smartisan.com/index.php?r=find/GetArticleList&cate_id=45&page_size=20";

    public static final String NEWS_EXPLORE_NEWS = "http://reader.smartisan.com/index.php?r=find/GetArticleList&cate_id=16&art_id=&page_size=20";

    public static final String NEWS_EXPLORE_COMMERCE = "http://reader.smartisan.com/index.php?r=find/GetArticleList&cate_id=34&art_id=&page_size=20";

    public static final String NEWS_EXPLORE_ART = "http://reader.smartisan.com/index.php?r=find/GetArticleList&cate_id=45&art_id=&page_size=20";

    public static final String NEWS_EXPLORE_TEC = "http://reader.smartisan.com/index.php?r=find/GetArticleList&cate_id=15&art_id=&page_size=20";

    public static final String NEWS_EXPLORE_LIFE = "http://reader.smartisan.com/index.php?r=find/GetArticleList&cate_id=11&art_id=&page_size=20";

    public static final String NEWS_EXPLORE_REAL = "http://reader.smartisan.com/index.php?r=find/GetArticleList&cate_id=44&art_id=&page_size=20";

    public static final String NEWS_EXPLORE_CHOICE = "http://reader.smartisan.com/index.php?r=find/GetArticleList&cate_id=999&page_size=20";

    public static final String NEWS_SUB_LIST = "http://reader.smartisan.com/index.php?r=myCenter/recommendList&offset=0&page_size=9";

    public static final String NEWS_SUB_LIST_ALL = "http://reader.smartisan.com/index.php?r=site/search&allList&offset=0&page_size=137";

    public static final String NEWS_SUB_LIST_IN_HEADER = "http://reader.smartisan.com/index.php?r=article/getList&site_id=";

    public static final String NEWS_SUB_LIST_IN_FOOTER = "&page_size=20&offset=";

    public static final String NEWS_SUB_GET_LIST = "http://reader.smartisan.com/index.php?r=visitor/getList&offset=0&page_size=20&site_ids=";

    /**
     * Banner
     * http://reader.smartisan.com/index.php?r=myCenter/show&site_ids=1,1810,1847
     * <p>
     * 推荐
     * http://reader.smartisan.com/index.php?r=myCenter/recommendList&offset=0&page_size=20
     * <p>
     * 站点
     * <p>
     * 新闻
     * http://reader.smartisan.com/index.php?r=site/search&cate_id=16&offset=0&page_size=20
     * 视频
     * http://reader.smartisan.com/index.php?r=site/search&cate_id=45&offset=0&page_size=20
     * 文艺
     * http://reader.smartisan.com/index.php?r=site/search&cate_id=10&offset=0&page_size=20
     * 商业
     * http://reader.smartisan.com/index.php?r=site/search&cate_id=34&offset=0&page_size=20
     * 科技
     * http://reader.smartisan.com/index.php?r=site/search&cate_id=15&offset=0&page_size=20
     * 生活
     * http://reader.smartisan.com/index.php?r=site/search&cate_id=11&offset=0&page_size=20
     * 科学
     * http://reader.smartisan.com/index.php?r=site/search&cate_id=43&offset=0&page_size=20
     * 真实
     * http://reader.smartisan.com/index.php?r=site/search&cate_id=44&offset=0&page_size=20
     */

    public static final String SITE_BASE = "http://reader.smartisan.com/index.php?r=site/search&cate_id=";

    public static final String SITE_NEWS = " http://reader.smartisan.com/index.php?r=site/search&cate_id=16&offset=0&page_size=20";

    public static final String SITE_VIDEO = " http://reader.smartisan.com/index.php?r=site/search&cate_id=45&offset=0&page_size=20";

    public static final String SITE_ART = " http://reader.smartisan.com/index.php?r=site/search&cate_id=10&offset=0&page_size=20";

    public static final String SITE_COMMERCE = " http://reader.smartisan.com/index.php?r=site/search&cate_id=34&offset=0&page_size=20";

    public static final String SITE_TEC = " http://reader.smartisan.com/index.php?r=site/search&cate_id=15&offset=0&page_size=20";

    public static final String SITE_LIFE = " http://reader.smartisan.com/index.php?r=site/search&cate_id=11&offset=0&page_size=20";

    public static final String SITE_REAL = " http://reader.smartisan.com/index.php?r=site/search&cate_id=44&offset=0&page_size=20";

    public static final String SITE_SCIENCE = " http://reader.smartisan.com/index.php?r=site/search&cate_id=44&offset=0&page_size=20";

    public static final String SITE_BANNER = "http://reader.smartisan.com/index.php?r=myCenter/show&site_ids=";

    public static final String SEARCH_RECOMMEND = "http://reader.smartisan.com/index.php?r=myCenter/recommendList&offset=0&page_size=6";

    public static final String SEARCH = "http://reader.smartisan.com/index.php?r=site/search&name=";

    /**
     * base url
     */

    public static final String BASE = "http://reader.smartisan.com";

    public static final String GET_SITE_INFO = "http://reader.smartisan.com/index.php?r=site/GetInfoById&site_id=";


}
