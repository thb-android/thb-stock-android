package com.thb.automatic.home.fragment.list.presenter;

import android.text.TextUtils;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.thb.automatic.app.IOTErrorHandleSubscriber;
import com.thb.automatic.app.utils.Utils;
import com.thb.automatic.home.fragment.list.contract.ListContract;
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
public class ListPresenter extends BasePresenter<ListContract.Model, ListContract.View> {

    private final static double rule = 1.0;

    @Inject
    RxErrorHandler mErrorHandler;

    private double mPercent;
    private boolean mIsNoTopLine;

    private int mTotal;
    private int mEffective;

    @Inject
    public ListPresenter(ListContract.Model model, ListContract.View view) {
        super(model, view);
    }

    public void loadData(String percent, boolean isNoTopLine) {
        mTotal = 0;
        mEffective = 0;

        if (!TextUtils.isEmpty(percent)) {
            mPercent = Double.valueOf(percent);
        } else {
            // 百分比默认是3%
            mPercent = 3.0;
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
                            if (isDoji(info)) {
                                mEffective++;
                                temp.add(info);
                            }
                        }
                        mRootView.updateView(temp);
                        String txt = "（最新价-开盘价）/ 开盘价 <= " + rule + "%\n";
                        mRootView.updateView(txt + "共" + mTotal + "条数据，其中波动幅度大于" + mPercent + "%有效数据共" + mEffective + "条");
                    }
                });
    }

    /**
     * 是否符合十字星
     */
    private boolean isDoji(StockInfo info) {
        final double abs = Math.abs(info.open - info.trade);
        final double absPercent = (abs / info.open) * 100.0;
        if (absPercent > rule) {
            return false;
        }

        if (info.open > info.trade) {
            return isMatch(info.open - info.high, info.open) || isMatch(info.trade - info.low, info.trade);
        } else {
            return isMatch(info.open - info.low, info.open) || isMatch(info.trade - info.high, info.trade);
        }
    }

    private boolean isMatch(double diff, double price) {
        return (Math.abs(diff) / price) * 100.0 > mPercent;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }

}
