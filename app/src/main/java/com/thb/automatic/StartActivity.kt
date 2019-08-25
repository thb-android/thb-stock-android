package com.thb.automatic

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.thb.automatic.service.arouter.ARouterPath.HOME_ACTIVITY

class StartActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().build(HOME_ACTIVITY).navigation()
        finish()
    }

}