package com.iot.automatic.modue.aboutus.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;
import butterknife.BindView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.iot.automatic.IOTBaseActivity;
import com.iot.automatic.R;
import com.iot.automatic.app.utils.Utils;
import com.iot.automatic.modue.aboutus.DaggerAboutUsComponent;
import com.iot.automatic.modue.aboutus.contract.AboutUsContract;
import com.iot.automatic.modue.aboutus.entity.AboutUsEntity;
import com.iot.automatic.modue.aboutus.presenter.AboutUsPresenter;
import com.iot.automatic.service.arouter.ARouterPath;
import com.jess.arms.di.component.AppComponent;


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
@Route(path = ARouterPath.ABOUT_US_ACTIVITY)
public class AboutUsActivity extends IOTBaseActivity<AboutUsPresenter> implements AboutUsContract.View {

    @BindView(R.id.about_text)
    TextView versionView;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerAboutUsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_about_us; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(Utils.getString(R.string.set_about_us));
        versionView.setText("当前版本V" + Utils.getAppVersion(this));
    }

    @Override
    public void updateView(AboutUsEntity entity) {
    }

    @Override
    public void killMyself() {
        finish();
    }

}
