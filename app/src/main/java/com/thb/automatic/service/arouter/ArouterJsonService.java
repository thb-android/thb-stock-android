package com.thb.automatic.service.arouter;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.fastjson.JSON;

import java.lang.reflect.Type;

/**
 * <p><p>
 * <p><p>
 * <p>作者：tanghuaibao<p>
 * <p>创建时间：2019-07-10<p>
 * <p>更改时间：2019-07-10<p>
 * <p>版本号：v1.0.0<p>
 */
@Route(path = "/service/json")
public class ArouterJsonService implements SerializationService {
    @Override
    public <T> T json2Object(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    @Override
    public String object2Json(Object instance) {
        return JSON.toJSONString(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return JSON.parseObject(input, clazz);
    }

    @Override
    public void init(Context context) {

    }
}
