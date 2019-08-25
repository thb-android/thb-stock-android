package com.thb.automatic.home.fragment.mine.entity;

import java.util.List;

public class MineEntity {

    public List<MineItem> items;

    public static class MineItem {
        // 先用int，如果后续改成服务端下发，那么再改
        public int imgId;
        public String text;
        /** 路由跳转路径 */
        public String aRouterPath;
    }
}
