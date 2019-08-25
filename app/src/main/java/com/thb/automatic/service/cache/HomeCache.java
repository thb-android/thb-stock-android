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
package com.thb.automatic.service.cache;

import com.thb.automatic.app.service.Result;
import com.thb.automatic.service.entity.DeviceInfo;
import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public interface HomeCache {

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Result<List<DeviceInfo>>>> getUsers(Observable<Result<List<DeviceInfo>>> users, DynamicKey params, EvictProvider evictProvider);
}
