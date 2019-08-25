package com.thb.automatic.app.utils.pref;

public class UserPreferences extends BasePreferences {

    public static final String KEY_TOKEN = "token";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_EXPIRE_TIME = "expire_time";
    public static final String KEY_ACCOUNT = "user_account";
    public static final String KEY_EMAIL = "user_email";
    public static final String KEY_PHONE = "user_phone";

    private static UserPreferences mInstance = null;

    public static UserPreferences getInstance() {
        if (null == mInstance) {
            mInstance = new UserPreferences();
        }
        return mInstance;
    }

    @Override
    String createSpName() {
        return "iot-user-pref";
    }

}