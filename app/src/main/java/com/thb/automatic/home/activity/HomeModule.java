package com.thb.automatic.home.activity;

import dagger.Binds;
import dagger.Module;

import com.thb.automatic.home.activity.contract.HomeContract;
import com.thb.automatic.home.activity.model.HomeModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 05/17/2019 10:50
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class HomeModule {

    @Binds
    abstract HomeContract.Model bindHomeModel(HomeModel model);
}