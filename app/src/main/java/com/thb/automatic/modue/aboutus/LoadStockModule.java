package com.thb.automatic.modue.aboutus;

import com.thb.automatic.modue.aboutus.contract.LoadStockContract;
import com.thb.automatic.modue.aboutus.model.LoadStockModel;
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
public abstract class LoadStockModule {

    @Binds
    abstract LoadStockContract.Model bindLoadStockModel(LoadStockModel model);
}