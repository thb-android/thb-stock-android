package com.thb.automatic.modue.loadstock.model;

import android.app.Application;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.thb.automatic.app.service.Constant;
import com.thb.automatic.modue.loadstock.contract.LoadStockContract;
import com.thb.automatic.modue.loadstock.entity.JuHeInfo;
import com.thb.automatic.service.HomeService;
import io.reactivex.Observable;

import javax.inject.Inject;
import java.util.HashMap;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/15/2019 19:52
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class LoadStockModel extends BaseModel implements LoadStockContract.Model {
    @Inject
    Application mApp;

    @Inject
    public LoadStockModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mApp = null;
    }

    @Override
    public Observable<JuHeInfo> getSZAll(int page) {
        HashMap<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("key", Constant.JUHE_KEY);
        params.put("type", String.valueOf(4));

        return mRepositoryManager
                .obtainRetrofitService(HomeService.class)
                .getSZAll(params);
    }

    @Override
    public Observable<JuHeInfo> getSHAll(int page) {
        HashMap<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("key", Constant.JUHE_KEY);
        params.put("type", String.valueOf(4));

        return mRepositoryManager
                .obtainRetrofitService(HomeService.class)
                .getSHAll(params);
    }
}