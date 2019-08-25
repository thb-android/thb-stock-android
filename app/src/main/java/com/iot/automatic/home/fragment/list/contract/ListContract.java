package com.iot.automatic.home.fragment.list.contract;

import com.iot.automatic.app.service.Result;
import com.iot.automatic.service.entity.DeviceInfo;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import io.reactivex.Observable;

import java.util.List;

public interface ListContract {

    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void onRefreshView();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,如是否使用缓存
    interface Model extends IModel {
        Observable<Result<List<DeviceInfo>>> getDevices(String userId);
    }
}
