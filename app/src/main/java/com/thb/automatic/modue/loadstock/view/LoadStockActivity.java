package com.thb.automatic.modue.loadstock.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;
import butterknife.BindView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;
import com.thb.automatic.IOTBaseActivity;
import com.thb.automatic.R;
import com.thb.automatic.app.utils.Utils;
import com.thb.automatic.modue.loadstock.DaggerLoadStockComponent;
import com.thb.automatic.modue.loadstock.contract.LoadStockContract;
import com.thb.automatic.modue.loadstock.presenter.LoadStockPresenter;
import com.thb.automatic.service.arouter.ARouterPath;


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
@Route(path = ARouterPath.LOAD_STOCK_ACTIVITY)
public class LoadStockActivity extends IOTBaseActivity<LoadStockPresenter> implements LoadStockContract.View {

    @BindView(R.id.load_net_text)
    TextView versionView;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoadStockComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_load_stock; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setTitle(Utils.getString(R.string.set_about_us));
        versionView.setText("当前版本V" + Utils.getAppVersion(this));
    }

    @Override
    public void killMyself() {
        finish();
    }

}
