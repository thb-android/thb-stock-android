package com.thb.automatic.modue.aboutus;

import com.thb.automatic.modue.aboutus.contract.AboutUsContract;
import com.thb.automatic.modue.aboutus.view.AboutUsActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import dagger.BindsInstance;
import dagger.Component;


/**
 * 关于我们
 */
@ActivityScope
@Component(modules = AboutUsModule.class, dependencies = AppComponent.class)
public interface AboutUsComponent {
    void inject(AboutUsActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        AboutUsComponent.Builder view(AboutUsContract.View view);

        AboutUsComponent.Builder appComponent(AppComponent appComponent);

        AboutUsComponent build();
    }
}