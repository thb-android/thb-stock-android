package com.thb.automatic.modue.kline.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import butterknife.BindView;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.di.component.AppComponent;
import com.thb.automatic.IOTBaseActivity;
import com.thb.automatic.R;
import com.thb.automatic.app.utils.Utils;
import com.thb.automatic.modue.kline.DaggerKLineComponent;
import com.thb.automatic.modue.kline.contract.KLineContract;
import com.thb.automatic.modue.kline.presenter.KLinePresenter;
import com.thb.automatic.service.arouter.ARouterPath;

import static com.thb.automatic.app.service.Constant.key_stock_symbol;


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
@Route(path = ARouterPath.K_LINE_ACTIVITY)
public class KLineActivity extends IOTBaseActivity<KLinePresenter> implements KLineContract.View {

    @BindView(R.id.k_line_img)
    ImageView mKLineImage;

    @Autowired(name = key_stock_symbol)
    String mStockSymbol;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerKLineComponent //如找不到该类,请编译一下项目
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
        ARouter.getInstance().inject(this);
        setTitle(Utils.getString(R.string.k_line_title));
    }

    @Override
    public void killMyself() {
        finish();
    }

}
