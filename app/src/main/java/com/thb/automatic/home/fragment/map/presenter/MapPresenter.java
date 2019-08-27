package com.thb.automatic.home.fragment.map.presenter;

import android.app.Application;
import android.text.TextUtils;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.thb.automatic.app.IOTErrorHandleSubscriber;
import com.thb.automatic.app.utils.Utils;
import com.thb.automatic.home.fragment.map.contract.MapContract;
import com.thb.automatic.home.fragment.map.entity.StockInfo;
import com.thb.automatic.service.CommonService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import okhttp3.ResponseBody;

import javax.inject.Inject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.thb.automatic.app.service.Constant.shanghai_file;
import static com.thb.automatic.app.service.Constant.shenzhen_file;

@FragmentScope
public class MapPresenter extends BasePresenter<MapContract.Model, MapContract.View> {

    @Inject
    List<String> tempData;
    @Inject
    Application mApplication;
    @Inject
    RxErrorHandler mErrorHandler;

    private double mPercent;
    private boolean mIsNoTopLine;

    private int mTotal;
    private int mEffective;

    @Inject
    public MapPresenter(MapContract.Model model, MapContract.View view) {
        super(model, view);
    }

    public void loadData(String percent, boolean isNoTopLine) {
        mTotal = 0;
        mEffective = 0;

        if (!TextUtils.isEmpty(percent)) {
            mPercent = Double.valueOf(percent);
        } else {
            // 百分比默认是1%
            mPercent = 1.0;
        }
        mIsNoTopLine = isNoTopLine;
        new Thread(() -> {
            final String path = Utils.getDirectory() + File.separator;
            loadSh(path);
            loadSz(path);
        }).start();

    }

    private void loadSh(String path) {
        try {
            char[] bt = new char[1024];
            StringBuilder sb = new StringBuilder();
            FileReader sh = new FileReader(path + shanghai_file);
            while (sh.read(bt) != -1) {
                sb.append(bt);
            }
            List<String> shangHai = new ArrayList<>(Arrays.asList(sb.toString().split(",")));
            Iterator<String> iterator = shangHai.iterator();

            int index = 0;
            sb.setLength(0);
            while (iterator.hasNext()) {
                sb.append(iterator.next()).append(",");
                iterator.remove();
                if ((index >= 10)) {
                    loadSinaStock(sb.toString());

                    index = 0;
                    sb.setLength(0);
                }
                index++;
            }
            loadSinaStock(sb.toString());
        } catch (FileNotFoundException e) {
            mRootView.updateView("请先在<我的>界面中初始化股票数据");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSz(String path) {
        try {
            char[] bt = new char[1024];
            StringBuilder sb = new StringBuilder();
            FileReader sh = new FileReader(path + shenzhen_file);
            while (sh.read(bt) != -1) {
                sb.append(bt);
            }
            List<String> shenzhen = new ArrayList<>(Arrays.asList(sb.toString().split(",")));
            Iterator<String> iterator = shenzhen.iterator();

            int index = 0;
            sb.setLength(0);
            while (iterator.hasNext()) {
                sb.append(iterator.next()).append(",");
                iterator.remove();
                if ((index >= 10)) {
                    loadSinaStock(sb.toString());

                    index = 0;
                    sb.setLength(0);
                }
                index++;
            }
            loadSinaStock(sb.toString());
        } catch (FileNotFoundException e) {
            mRootView.updateView("请先在<我的>界面中初始化股票数据");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSinaStock(String symbol) {
        mModel.getSinal(CommonService.SINA_URL + symbol)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .doOnSubscribe(disposable -> {
                    mRootView.showLoading();//显示进度条
                })
                .doFinally(() -> {
                    mRootView.hideLoading();//隐藏下拉刷新的进度条
                })
                .subscribe(new IOTErrorHandleSubscriber<ResponseBody>(mErrorHandler) {
                    @Override
                    public void onResponse(ResponseBody response) {
                        final List<StockInfo> infos = Utils.pares2Stock(response.byteStream());
                        final List<StockInfo> temp = new ArrayList<>();
                        for (StockInfo info : infos) {
                            mTotal++;
                            if (shouldAdd(info)) {
                                mEffective++;
                                temp.add(info);
                            }
                        }
                        mRootView.updateView(temp);
                        mRootView.updateView("共" + mTotal + "条数据，其中涨幅大于" + mPercent + "%有效数据共" + mEffective + "条");
                    }
                });
    }

    private boolean shouldAdd(StockInfo info) {
        if (info.open <= info.trade) {
            // 开盘价小于等于最新价格，不符合要求
            return false;
        }

        if (info.low <= info.settlement) {
            // 最低价小于等于昨日收盘价格，不符合要求
            return false;
        }

        if (info.changepercent < mPercent) {
            // 涨幅小于规定的百分比，不符合要求
            return false;
        }

        if ((info.open < info.high) && mIsNoTopLine) {
            // 如果条件不允许有有上阴线，但实际有上阴线，不符合要求
            return false;
        }

        if (info.name.contains("ST")) {
            // 名称包含ST，不符合要求
            return false;
        }

        return true;
    }

}
