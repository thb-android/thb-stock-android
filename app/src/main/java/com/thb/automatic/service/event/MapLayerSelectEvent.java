package com.thb.automatic.service.event;

public class MapLayerSelectEvent {
    public static final int HOME_MAP_TYPE = 0;
    public static final int DEVICE_MAP_TYPE = 1;
    public static final int TRACK_ACTIVITY_TYPE = 2;

    public String selectedLayer;
    public int type;

    public MapLayerSelectEvent(String key, int type) {
        this.selectedLayer = key;
        this.type = type;
    }
}
