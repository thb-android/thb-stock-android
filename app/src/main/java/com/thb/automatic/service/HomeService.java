package com.thb.automatic.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface HomeService {
    @GET
    Observable<ResponseBody> getSina(@Url String url);
}
