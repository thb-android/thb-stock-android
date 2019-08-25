package com.thb.automatic.app;

import com.thb.automatic.app.service.Result;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.thb.automatic.app.service.Constant.ERROR_TOKEN;

/**
 * <p><p>
 * <p><p>
 * <p>作者：tanghuaibao<p>
 * <p>创建时间：2019-06-21<p>
 * <p>更改时间：2019-06-21<p>
 * <p>版本号：v1.0.0<p>
 */
public abstract class IOTErrorHandleSubscriber<T> extends ErrorHandleSubscriber<T> {

    public IOTErrorHandleSubscriber(RxErrorHandler rxErrorHandler) {
        super(rxErrorHandler);
    }


    @Override
    public void onNext(T res) {
        if (null == res) {
            return;
        }

        boolean isNeedGO = true;

        if (res instanceof Result) {
            Result temp = (Result) res;
            if (temp.code == ERROR_TOKEN) {
                isNeedGO = false;
            }
        }

        if (isNeedGO) {
            this.onResponse(res);
        }
    }

    public abstract void onResponse(T response);
}
