package com.thb.automatic.modue.loadstock;

import com.thb.automatic.modue.loadstock.contract.LoadStockContract;
import com.thb.automatic.modue.loadstock.view.LoadStockActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import dagger.BindsInstance;
import dagger.Component;


/**
 * 关于我们
 */
@ActivityScope
@Component(modules = LoadStockModule.class, dependencies = AppComponent.class)
public interface LoadStockComponent {
    void inject(LoadStockActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        LoadStockComponent.Builder view(LoadStockContract.View view);

        LoadStockComponent.Builder appComponent(AppComponent appComponent);

        LoadStockComponent build();
    }
}