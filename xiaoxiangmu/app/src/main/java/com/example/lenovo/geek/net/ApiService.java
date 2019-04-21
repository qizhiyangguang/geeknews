package com.example.lenovo.geek.net;

import com.example.lenovo.geek.bean.DailyNewsBean;
import com.example.lenovo.geek.bean.HotBean;
import com.example.lenovo.geek.bean.LoginBean;
import com.example.lenovo.geek.bean.Sections;
import com.example.lenovo.geek.bean.SectionsBean;
import com.example.lenovo.geek.bean.WeChatBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    String uBaseUrl = "http://yun918.cn/study/public/index.php/";
    String url1 = "http://api.tianapi.com/wxnew/";

    @FormUrlEncoded
    @POST("login")
    Observable<LoginBean> login(@Field("username") String name, @Field("password") String psd);

    String url = "http://news-at.zhihu.com/api/4/";

    @GET("news/latest")
    Observable<DailyNewsBean> getLastDailyNews();

    @GET("news/hot")
    Observable<HotBean> getHot();

    @GET("sections")
    Observable<SectionsBean> getSections();

    @GET("section/{page}")
    Observable<Sections> getSectionss(@Path("page") int page);


    @GET("?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1")
    Observable<WeChatBean> getWechat();

}
