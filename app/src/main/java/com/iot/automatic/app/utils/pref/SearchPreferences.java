package com.iot.automatic.app.utils.pref;

public class SearchPreferences extends BasePreferences {

    public static final String KEY_SEARCH_HISTORY = "search_history";

    private static SearchPreferences mInstance = null;

    public static SearchPreferences getInstance() {
        if (null == mInstance) {
            mInstance = new SearchPreferences();
        }
        return mInstance;
    }

    @Override
    String createSpName() {
        return "iot-search-pref";
    }

}
