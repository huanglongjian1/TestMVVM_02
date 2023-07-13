package com.android.test2mvvm.test1.retrofit;

import java.util.List;

public class javabean {

    /**
     * images : [{"startdate":"20230528","fullstartdate":"202305281600","enddate":"20230529","url":"/th?id=OHR.Antilles_ZH-CN8267285876_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp","urlbase":"/th?id=OHR.Antilles_ZH-CN8267285876","copyright":"萨克马兰大湾，瓜德罗普岛国家公园，小安的列斯群岛 (© Hemis/Alamy)","copyrightlink":"https://www.bing.com/search?q=%E7%93%9C%E5%BE%B7%E7%BD%97%E6%99%AE%E5%B2%9B&form=hpcapt&mkt=zh-cn","title":"人间天堂","quiz":"/search?q=Bing+homepage+quiz&filters=WQOskey:%22HPQuiz_20230528_Antilles%22&FORM=HPQUIZ","wp":true,"hsh":"eb4c793852f388c10e7f44c2e9f6777f","drk":1,"top":1,"bot":1,"hs":[]}]
     * tooltips : {"loading":"正在加载...","previous":"上一个图像","next":"下一个图像","walle":"此图片不能下载用作壁纸。","walls":"下载今日美图。仅限用作桌面壁纸。"}
     */

    private TooltipsBean tooltips;
    private List<ImagesBean> images;

    public TooltipsBean getTooltips() {
        return tooltips;
    }

    public void setTooltips(TooltipsBean tooltips) {
        this.tooltips = tooltips;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }
}
