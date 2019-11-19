package com.allever.app.ad.app

//import com.allever.app.ad.AdConstants
import com.allever.lib.ad.AdHelper
import com.allever.lib.ad.admob.AdMobHelper
//import com.allever.lib.ad.admob_lite.AdMobLiteHelper
//import com.allever.lib.ad.admob_lite.AdMobLiteHelper
import com.allever.lib.ad.wanpu.WanPuAdHelper
//import com.allever.lib.ad.mimo.MiMoAdHelper
import com.allever.lib.common.app.App

class MyApp: App() {
    override fun onCreate() {
        super.onCreate()
        AdHelper.init(this, AdMobHelper, "")
    }
}