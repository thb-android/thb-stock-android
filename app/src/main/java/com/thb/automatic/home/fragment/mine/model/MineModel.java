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
package com.thb.automatic.home.fragment.mine.model;

import android.app.Application;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.jess.arms.utils.ArmsUtils;
import com.thb.automatic.R;
import com.thb.automatic.home.fragment.mine.contract.MineContract;
import com.thb.automatic.home.fragment.mine.entity.MineEntity;
import com.thb.automatic.service.arouter.ARouterPath;
import io.reactivex.Observable;

import javax.inject.Inject;
import java.util.ArrayList;

@FragmentScope
public class MineModel extends BaseModel implements MineContract.Model {

    @Inject
    Application mApp;

    @Inject
    public MineModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<MineEntity> getTempData() {
        MineEntity entity = new MineEntity();
        entity.items = new ArrayList<>();

        MineEntity.MineItem item3 = new MineEntity.MineItem();
        item3.imgId = R.drawable.ic_fence_admin;
        item3.text = ArmsUtils.getString(mApp, R.string.mine_init);
        item3.aRouterPath = ARouterPath.LOAD_STOCK_ACTIVITY;
        entity.items.add(item3);

        return Observable.just(entity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mApp = null;
    }

}
