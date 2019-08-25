/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iot.automatic.home.fragment.list.model;

import com.iot.automatic.app.service.Result;
import com.iot.automatic.home.fragment.list.contract.ListContract;
import com.iot.automatic.service.entity.DeviceInfo;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import io.reactivex.Observable;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;

/**
 * ================================================
 * 展示 Model 的用法
 *
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki#2.4.3">Model wiki 官方文档</a>
 * Created by JessYan on 09/04/2016 10:56
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
@FragmentScope
public class ListModel extends BaseModel implements ListContract.Model {

    @Inject
    public ListModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<Result<List<DeviceInfo>>> getDevices(String userId) {
        HashMap<String, String> params = new HashMap<>();
        params.put("method", "www.iot-icloud.cn.user.device.list");
        params.put("target", userId);

        return null;
    }
}
