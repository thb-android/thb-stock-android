package com.iot.automatic.service.arouter;

public interface ARouterPath {
    String HOME_ACTIVITY = "/app/home_activity";
    String LOGIN_ACTIVITY = "/app/login_activity";
    // 更多操作界面（轨迹、指令、围栏等等）
    String OPERATION_ACTIVITY = "/app/operation_activity";
    // 设置界面
    String SETTING_ACTIVITY = "/app/setting_activity";
    // 告警信息列表界面
    String ALARM_LIST_ACTIVITY = "/app/alarm_list_activity";
    // 我的服务商
    String MY_SP_ACTIVITY = "/app/my_sp_activity";
    // 关于我们
    String ABOUT_US_ACTIVITY = "/app/about_us_activity";
    // 设备地图界面，点击某个设备进入的地图界面
    String DEVICE_MAP_ACTIVITY = "/app/device_map_activity";
    // 个人资料
    String PERSONAL_ACTIVITY = "/app/personal_activity";
    // 围栏列表
    String FENCE_ACTIVITY = "/app/fence_activity";
    // 围栏管理
    String FENCE_MANAGER_ACTIVITY = "/app/fence_manager_activity";
    String FENCE_ADD_ACTIVITY = "/app/fence_add_activity";
    String TRACK_ACTIVITY = "/app/track_activity";
    String TRACK_FOLLOW_ACTIVITY = "/app/track_follow_activity";

    // 控制台
    String CONSOLE_ACTIVITY = "/app/console_activity";
    /**
     * 修改资料组件
     * 修改账号，修改密码、修改电话、修改邮箱等
     */
    String MODIFIED_ACTIVITY = "/app/modified_activity";
    // 搜索界面
    String SEARCH_ACTIVITY = "/app/search_activity";
    // 搜索结果界面
    String SEARCH_RESULT_ACTIVITY = "/app/search_result_activity";
    // 添加设备界面
    String ADD_DEVICE_ACTIVITY = "/app/add_device_activity";
    // 围栏添加设备界面，包括围栏信息
    String FENCE_PROPERTY_SET_ACTIVITY = "/app/fence_property_set_activity";
    // 设备详情界面
    String DEVICE_INFO_ACTIVITY = "/app/device_info_activity";
    // 指令界面，左侧指令列表，右侧指令操作
    String DIRECTIVE_ACTIVITY = "/app/directive_activity";
}
