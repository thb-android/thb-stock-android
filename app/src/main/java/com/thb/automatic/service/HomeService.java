package com.thb.automatic.service;

import com.thb.automatic.modue.loadstock.entity.JuHeInfo;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

import java.util.HashMap;

public interface HomeService {
    /**
     * 新浪股票接口
     */
    @GET
    Observable<ResponseBody> getSina(@Url String url);

    /**
     * 上海
     */
    @GET(CommonService.SHALL_URL)
    Observable<JuHeInfo> getSHAll(@QueryMap HashMap<String, String> params);

    /**
     * 深圳
     */
    @GET(CommonService.SZALL_URL)
    Observable<JuHeInfo> getSZAll(@QueryMap HashMap<String, String> params);
}
