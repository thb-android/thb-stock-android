package com.thb.automatic.home.fragment.map.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.thb.automatic.home.fragment.map.entity.StockInfo;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

import java.util.List;

public interface MapContract {

    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void updateView(List<StockInfo> infos);
        void updateView(String error);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,如是否使用缓存
    interface Model extends IModel {
        Observable<ResponseBody> getSinal(String url);
    }
}
