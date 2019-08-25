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
package com.iot.automatic.home.fragment.mine.model;

import android.app.Application;
import com.iot.automatic.home.fragment.mine.contract.MineContract;
import com.iot.automatic.home.fragment.mine.entity.MineEntity;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
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

//        MineEntity.MineItem item1 = new MineEntity.MineItem();
//        item1.imgId = R.drawable.ic_server_provider;
//        item1.text = ArmsUtils.getString(mApp, R.string.mine_my_service_provider);
//        item1.aRouterPath = ARouterPath.MY_SP_ACTIVITY;

//        MineEntity.MineItem item2 = new MineEntity.MineItem();
//        item2.imgId = R.drawable.ic_cmd_journal;
//        item2.text = ArmsUtils.getString(mApp, R.string.mine_log_directive);

//        MineEntity.MineItem item3 = new MineEntity.MineItem();
//        item3.imgId = R.drawable.ic_fence_admin;
//        item3.text = ArmsUtils.getString(mApp, R.string.mine_rail_manager);
//        item3.aRouterPath = ARouterPath.FENCE_MANAGER_ACTIVITY;

//        MineEntity.MineItem item4 = new MineEntity.MineItem();
//        item4.imgId = R.drawable.ic_common_problem;
//        item4.text = ArmsUtils.getString(mApp, R.string.mine_question);

        // TODO: 2019-07-01 这个应该是设置，而不是关于我们
//        MineEntity.MineItem item5 = new MineEntity.MineItem();
//        item5.imgId = R.drawable.ic_mine_setting;
//        item5.text = ArmsUtils.getString(mApp, R.string.mine_setting);
//        item5.aRouterPath = ARouterPath.SETTING_ACTIVITY;

//        MineEntity.MineItem item5 = new MineEntity.MineItem();
//        item5.imgId = R.drawable.setting_page_about;
//        item5.text = ArmsUtils.getString(mApp, R.string.set_about_us);
//        item5.aRouterPath = ARouterPath.ABOUT_US_ACTIVITY;

        entity.items = new ArrayList<>(5);

//        entity.items.add(item1);
//        entity.items.add(item2);
//        entity.items.add(item3);
//        entity.items.add(item4);
//        entity.items.add(item5);

        return Observable.just(entity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mApp = null;
    }

}
