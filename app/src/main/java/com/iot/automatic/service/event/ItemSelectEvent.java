package com.iot.automatic.service.event;

/**
 * <p><p>
 * <p>eventbus选中或者取消事件<p>
 * <p>作者：tanghuaibao<p>
 * <p>创建时间：2019-06-28<p>
 * <p>更改时间：2019-06-28<p>
 * <p>版本号：v1.0.0<p>
 */
public class ItemSelectEvent {
    public final boolean isSelect;

    public ItemSelectEvent(boolean isSelect) {
        this.isSelect = isSelect;
    }
}
