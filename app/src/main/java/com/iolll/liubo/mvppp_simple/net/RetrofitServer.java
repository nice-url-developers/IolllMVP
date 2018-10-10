package com.iolll.liubo.mvppp_simple.net;


import com.iolll.liubo.mvppp_simple.model.Result;
import com.iolll.liubo.mvppp_simple.model.net.GankRoot;
import com.iolll.liubo.mvppp_simple.model.net.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by lc on 2016/11/8
 */

public interface RetrofitServer {
    @POST("user/login")
    @FormUrlEncoded
    Observable<Result<User>> login (@Field("userName") String name, @Field("passWord") String pwd);

    @GET("https://gank.io/api/data/福利/10/1")
    Observable<GankRoot> getDaily();

}
