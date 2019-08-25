package com.thb.automatic.modue.kline;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.thb.automatic.modue.kline.contract.KLineContract;
import com.thb.automatic.modue.kline.view.KLineActivity;
import dagger.BindsInstance;
import dagger.Component;


/**
 * 关于我们
 */
@ActivityScope
@Component(modules = KLineModule.class, dependencies = AppComponent.class)
public interface KLineComponent {
    void inject(KLineActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        KLineComponent.Builder view(KLineContract.View view);

        KLineComponent.Builder appComponent(AppComponent appComponent);

        KLineComponent build();
    }
}