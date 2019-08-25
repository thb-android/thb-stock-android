package com.iot.automatic.modue.aboutus.model;

import android.app.Application;
import com.iot.automatic.modue.aboutus.contract.AboutUsContract;
import com.iot.automatic.modue.aboutus.entity.AboutUsEntity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import io.reactivex.Observable;

import javax.inject.Inject;


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
public class AboutUsModel extends BaseModel implements AboutUsContract.Model {
    @Inject
    Application mApp;

    @Inject
    public AboutUsModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<AboutUsEntity> getAboutUsData() {


        return Observable.just(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mApp = null;
    }

}