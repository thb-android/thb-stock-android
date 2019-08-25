package com.thb.automatic.modue.loadstock.presenter;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.thb.automatic.app.IOTErrorHandleSubscriber;
import com.thb.automatic.app.utils.Utils;
import com.thb.automatic.home.fragment.map.entity.StockInfo;
import com.thb.automatic.modue.loadstock.contract.LoadStockContract;
import com.thb.automatic.modue.loadstock.entity.JuHeInfo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

import javax.inject.Inject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.thb.automatic.app.service.Constant.shanghai_file;
import static com.thb.automatic.app.service.Constant.shenzhen_file;


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
@ActivityScope
public class LoadStockPresenter extends BasePresenter<LoadStockContract.Model, LoadStockContract.View> {
    private static final int max_count = 80;

    private static final int msg_sz = 1;
    private static final int msg_sh = 2;

    private File mSzFile;
    private File mShFile;

    private String mShenZhenTxt = "深圳";
    private String mShangHaiTxt = "上海";

    @Inject
    RxErrorHandler mErrorHandler;

    private int sz_page = 0;
    private int sh_page = 0;

    private Handler mHandler;

    @Inject
    public LoadStockPresenter(LoadStockContract.Model model, LoadStockContract.View rootView) {
        super(model, rootView);
        final HandlerThread tempThread = new HandlerThread("stock_handler_thread");
        tempThread.start();
        mHandler = new Handler(tempThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == msg_sz) {
                    write2Sz((String) msg.obj);
                } else if (msg.what == msg_sh) {
                    write2Sh((String) msg.obj);
                }
            }
        };
        File temp = new File(Utils.getDirectory());
        if (!temp.exists()) {
            temp.mkdirs();
        }
    }

    private void write2Sh(String content) {
        final String path = Utils.getDirectory();
        if (mShFile == null) {
            mShFile = new File(path, shanghai_file);

            if (mShFile.exists()) {
                mShFile.delete();
            }
            try {
                mShFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileWriter writer = new FileWriter(mShFile, true);
            BufferedWriter bufWriter = new BufferedWriter(writer);
            bufWriter.write(content);
            bufWriter.flush();
            bufWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write2Sz(String content) {
        final String path = Utils.getDirectory();
        if (mSzFile == null) {
            mSzFile = new File(path, shenzhen_file);

            if (mSzFile.exists()) {
                mSzFile.delete();
            }

            try {
                mSzFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileWriter writer = new FileWriter(mSzFile, true);
            BufferedWriter bufWriter = new BufferedWriter(writer);
            bufWriter.write(content);
            bufWriter.flush();
            bufWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadStock() {
        getSH();
        getSZ();
    }

    private void getSH() {
        mModel.getSHAll(sh_page).subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new IOTErrorHandleSubscriber<JuHeInfo>(mErrorHandler) {
                    @Override
                    public void onResponse(JuHeInfo info) {
                        StringBuilder content = new StringBuilder();
                        if (info.result.data != null) {
                            for (StockInfo temp : info.result.data) {
                                content.append(temp.symbol).append(",");
                            }
                        }

                        Message msg = mHandler.obtainMessage(msg_sh);
                        msg.obj = content.toString();
                        mHandler.sendMessage(msg);

                        sh_page++;
                        mShangHaiTxt = "正在处理上海第" + sh_page + "页，共" + info.result.totalCount + "条数据";
                        mRootView.updateView(mShangHaiTxt + "\n" + mShenZhenTxt);
                        if (info.result.page * sh_page < info.result.totalCount) {
                            getSH();
                        }
                    }
                });
    }

    private void getSZ() {
        mModel.getSZAll(sz_page).subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new IOTErrorHandleSubscriber<JuHeInfo>(mErrorHandler) {
                    @Override
                    public void onResponse(JuHeInfo info) {
                        StringBuilder content = new StringBuilder();
                        if (info.result.data != null) {
                            for (StockInfo temp : info.result.data) {
                                content.append(temp.symbol).append(",");
                            }
                        }

                        Message msg = mHandler.obtainMessage(msg_sz);
                        msg.obj = content.toString();
                        mHandler.sendMessage(msg);

                        sz_page++;
                        mShenZhenTxt = "正在处理深圳第" + sz_page + "页，共" + info.result.totalCount + "条数据";
                        mRootView.updateView(mShangHaiTxt + "\n" + mShenZhenTxt);
                        if (info.result.page * sz_page < info.result.totalCount) {
                            getSZ();
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }
}
