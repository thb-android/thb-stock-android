package com.iot.automatic.service;

import com.iot.automatic.app.service.Result;
import com.iot.automatic.home.fragment.map.entity.DeviceState;
import com.iot.automatic.home.fragment.map.entity.LocationListEntity;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.HashMap;
import java.util.List;

import static com.iot.automatic.service.CommonService.COMMON_URL;

public interface HomeService {
    @GET(COMMON_URL)
    Observable<Result<DeviceState>> getDeviceState(@QueryMap HashMap<String, String> params);

    @GET(COMMON_URL)
    Observable<Result<List<LocationListEntity>>> getLocationList(@QueryMap HashMap<String, String> params);
}
