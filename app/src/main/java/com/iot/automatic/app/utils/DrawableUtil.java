package com.iot.automatic.app.utils;

import android.graphics.drawable.Drawable;
import com.iot.automatic.R;
import com.iot.automatic.app.Common;
import com.jess.arms.utils.ArmsUtils;

import java.util.HashMap;

/**
 * <p><p>
 * <p><p>
 * <p>作者：tanghuaibao<p>
 * <p>创建时间：2019-07-04<p>
 * <p>更改时间：2019-07-04<p>
 * <p>版本号：v1.0.0<p>
 */
public class DrawableUtil {

    // ---------------地图图标 start --------------------------------
    private static HashMap<Integer, Integer> markMap = new HashMap();

    static {
        //电动三轮车 0-离线，1-停车/静止，2-行驶/移动，3-失联，4-报警
        markMap.put(1 << 4 | 0, R.drawable.electric_tricycle_car_offline);
        markMap.put(1 << 4 | 1, R.drawable.electric_tricycle_car_stop);
        markMap.put(1 << 4 | 2, R.drawable.electric_tricycle_car_move);
        markMap.put(1 << 4 | 3, R.drawable.electric_tricycle_car_outofcontact);
        markMap.put(1 << 4 | 4, R.drawable.electric_tricycle_car_fault);
        //轿车
        markMap.put(3 << 4 | 0, R.drawable.car_offline);
        markMap.put(3 << 4 | 1, R.drawable.car_stop);
        markMap.put(3 << 4 | 2, R.drawable.car_move);
        markMap.put(3 << 4 | 3, R.drawable.car_outofcontact);
        markMap.put(3 << 4 | 4, R.drawable.car_fault);
        //货车
        markMap.put(5 << 4 | 0, R.drawable.truck_offline);
        markMap.put(5 << 4 | 1, R.drawable.truck_stop);
        markMap.put(5 << 4 | 2, R.drawable.truck_move);
        markMap.put(5 << 4 | 3, R.drawable.truck_outofcontact);
        markMap.put(5 << 4 | 4, R.drawable.truck_fault);
        //罐箱
        markMap.put(12 << 4 | 0, R.drawable.tank_offline);
        markMap.put(12 << 4 | 1, R.drawable.tank_stop);
        markMap.put(12 << 4 | 2, R.drawable.tank_move);
        markMap.put(12 << 4 | 3, R.drawable.tank_outofcontact);
        markMap.put(12 << 4 | 4, R.drawable.tank_fault);
        //人员
        markMap.put(31 << 4 | 0, R.drawable.people_offline);
        markMap.put(31 << 4 | 1, R.drawable.people_stop);
        markMap.put(31 << 4 | 2, R.drawable.people_move);
        markMap.put(31 << 4 | 3, R.drawable.people_outofcontact);
        markMap.put(31 << 4 | 4, R.drawable.people_fault);
        //其他
        markMap.put(99 << 4 | 0, R.drawable.other_offline);
        markMap.put(99 << 4 | 1, R.drawable.other_stop);
        markMap.put(99 << 4 | 2, R.drawable.other_move);
        markMap.put(99 << 4 | 3, R.drawable.other_outofcontact);
        markMap.put(99 << 4 | 4, R.drawable.other_fault);
    }

    public static Drawable getMarkerDrawable(int applyTo, int state) {
        int key = applyTo << 4 | state;
        //默认其他
        int id = markMap.get(99 << 4 | state);
        if (markMap.containsKey(key)) {
            id = markMap.get(key);
        }
        return ArmsUtils.getResources(Common.getInstance().getContext()).getDrawable(id);
    }

    public static int getMarkerDrawableId(int applyTo, int state) {
        int key = applyTo << 4 | state;
        //默认其他
        int id = markMap.get(99 << 4 | state);
        if (markMap.containsKey(key)) {
            id = markMap.get(key);
        }
        return id;
    }

    //电动三轮车 0-离线，1-停车/静止，2-行驶/移动，3-失联，4-报警
    public static Drawable getInfoWindowDrawableByState(int state) {
        Drawable drawable = ArmsUtils.getResources(Common.getInstance().getContext()).getDrawable(R.drawable.device_home_mark_bg_offline);
        switch (state) {
            case 0:
                break;
            case 3:
                drawable = ArmsUtils.getResources(Common.getInstance().getContext()).getDrawable(R.drawable.device_home_mark_bg_outofcontact);
                break;
            case 4:
                drawable = ArmsUtils.getResources(Common.getInstance().getContext()).getDrawable(R.drawable.device_home_mark_bg_fault);
                break;
            case 1:
                drawable = ArmsUtils.getResources(Common.getInstance().getContext()).getDrawable(R.drawable.device_home_mark_bg_stop);
                break;
            case 2:
                drawable = ArmsUtils.getResources(Common.getInstance().getContext()).getDrawable(R.drawable.device_home_mark_bg_move);
                break;
        }
        return drawable;
    }

    public static int getInfoWidowColorByState(int state) {
        int color = ArmsUtils.getResources(Common.getInstance().getContext()).getColor(R.color.info_window_offline);
        switch (state) {
            case 0:
                break;
            case 3:
                color = ArmsUtils.getResources(Common.getInstance().getContext()).getColor(R.color.info_window_outofcontact);
                break;
            case 4:
                color = ArmsUtils.getResources(Common.getInstance().getContext()).getColor(R.color.info_window_fault);
                break;
            case 1:
                color = ArmsUtils.getResources(Common.getInstance().getContext()).getColor(R.color.info_window_stop);
                break;
            case 2:
                color = ArmsUtils.getResources(Common.getInstance().getContext()).getColor(R.color.info_window_move);
                break;
        }
        return color;
    }

    // ---------------地图图标  end --------------------------------

    // ---------------列表页图标 start --------------------------------
    private static HashMap<Integer, Integer> listCarImg = new HashMap();

    static {
        //电动三轮车 0-未激活(默认)，1-正常使用，2-故障，3-返修中，4-报废，5-其它
        listCarImg.put(1 << 4 | 0, R.drawable.ic_electrocar_offline);
        listCarImg.put(1 << 4 | 1, R.drawable.ic_electrocar_online);
        listCarImg.put(1 << 4 | 2, R.drawable.ic_electrocar_online);
        listCarImg.put(1 << 4 | 3, R.drawable.ic_electrocar_offline);
        listCarImg.put(1 << 4 | 4, R.drawable.ic_electrocar_online);
        //轿车
        listCarImg.put(3 << 4 | 0, R.drawable.ic_car_offline);
        listCarImg.put(3 << 4 | 1, R.drawable.ic_car_online);
        listCarImg.put(3 << 4 | 2, R.drawable.ic_car_online);
        listCarImg.put(3 << 4 | 3, R.drawable.ic_car_offline);
        listCarImg.put(3 << 4 | 4, R.drawable.ic_car_online);
        //轿车
        listCarImg.put(5 << 4 | 0, R.drawable.ic_trucks_offline);
        listCarImg.put(5 << 4 | 1, R.drawable.ic_trucks_online);
        listCarImg.put(5 << 4 | 2, R.drawable.ic_trucks_online);
        listCarImg.put(5 << 4 | 3, R.drawable.ic_trucks_offline);
        listCarImg.put(5 << 4 | 4, R.drawable.ic_trucks_online);
        //罐箱
        listCarImg.put(12 << 4 | 0, R.drawable.ic_tank_container_offline);
        listCarImg.put(12 << 4 | 1, R.drawable.ic_tank_container_online);
        listCarImg.put(12 << 4 | 2, R.drawable.ic_tank_container_online);
        listCarImg.put(12 << 4 | 3, R.drawable.ic_tank_container_offline);
        listCarImg.put(12 << 4 | 4, R.drawable.ic_tank_container_online);
        //人员
        listCarImg.put(31 << 4 | 0, R.drawable.ic_people_offline);
        listCarImg.put(31 << 4 | 1, R.drawable.ic_people_online);
        listCarImg.put(31 << 4 | 2, R.drawable.ic_people_online);
        listCarImg.put(31 << 4 | 3, R.drawable.ic_people_offline);
        listCarImg.put(31 << 4 | 4, R.drawable.ic_people_online);
        //其他
        listCarImg.put(99 << 4 | 0, R.drawable.ic_other_offline);
        listCarImg.put(99 << 4 | 1, R.drawable.ic_other_online);
        listCarImg.put(99 << 4 | 2, R.drawable.ic_other_online);
        listCarImg.put(99 << 4 | 3, R.drawable.ic_other_offline);
        listCarImg.put(99 << 4 | 4, R.drawable.ic_other_online);
    }

    public static int getListIcon(int applyTo, int state) {
        int key = applyTo << 4 | state;
        //默认其他
        Integer id = listCarImg.get(99 << 4 | state);
        if (listCarImg.containsKey(key)) {
            id = listCarImg.get(key);
        }
        return id == null ? R.drawable.ic_other_offline : id;
    }
    // ---------------列表页图标  end --------------------------------
}
