package com.thb.automatic.home.activity;

import dagger.BindsInstance;
import dagger.Component;
import com.jess.arms.di.component.AppComponent;

import com.thb.automatic.home.activity.contract.HomeContract;

import com.jess.arms.di.scope.ActivityScope;
import com.thb.automatic.home.activity.view.HomeActivity;


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
@ActivityScope
@Component(modules = HomeModule.class, dependencies = AppComponent.class)
public interface HomeComponent {
    void inject(HomeActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        HomeComponent.Builder view(HomeContract.View view);

        HomeComponent.Builder appComponent(AppComponent appComponent);

        HomeComponent build();
    }
}