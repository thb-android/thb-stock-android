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
package com.thb.automatic.home.fragment.map;

import com.thb.automatic.home.fragment.map.contract.MapContract;
import com.thb.automatic.home.fragment.map.model.MapModel;
import com.jess.arms.di.scope.FragmentScope;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * K线筛选
 *
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki#2.4.5">Module wiki 官方文档</a>
 * Created by JessYan on 09/04/2016 11:10
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
@Module
public abstract class MapModule {

    @Binds
    abstract MapContract.Model bindMapModel(MapModel model);

    @FragmentScope
    @Provides
    static List<String> provideTempDataList() {
        return new ArrayList<>();
    }

}
