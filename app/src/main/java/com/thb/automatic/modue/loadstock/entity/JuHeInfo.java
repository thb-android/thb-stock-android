package com.thb.automatic.modue.loadstock.entity;

import com.thb.automatic.home.fragment.map.entity.StockInfo;

import java.util.List;

/**
 * <p><p>
 * <p><p>
 * <p>作者：tanghuaibao<p>
 * <p>创建时间：2019-08-25<p>
 * <p>更改时间：2019-08-25<p>
 * <p>版本号：v1.0.0<p>
 */
public class JuHeInfo {
    public int error_code;
    public String reason;
    public Result result;

    public class Result {
        public int totalCount;
        public int page;
        public int num;
        public List<StockInfo> data;
    }
}
