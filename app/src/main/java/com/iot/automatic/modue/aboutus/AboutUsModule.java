package com.iot.automatic.modue.aboutus;

import com.iot.automatic.modue.aboutus.contract.AboutUsContract;
import com.iot.automatic.modue.aboutus.model.AboutUsModel;
import dagger.Binds;
import dagger.Module;


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
@Module
public abstract class AboutUsModule {

    @Binds
    abstract AboutUsContract.Model bindAboutUsModel(AboutUsModel model);
}