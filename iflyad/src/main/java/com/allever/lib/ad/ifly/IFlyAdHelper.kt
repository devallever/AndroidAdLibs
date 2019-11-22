package com.allever.lib.ad.ifly

import android.content.Context
import com.allever.lib.ad.ADType
import com.allever.lib.ad.AdManager
import com.allever.lib.ad.BaseAd
import com.allever.lib.common.app.App
import com.iflytek.voiceads.config.SDKLogger
import com.iflytek.voiceads.dex.DexLoader

object IFlyAdHelper: AdManager() {
    override fun init(context: Context, appId: String, appKey: String, appToken: String) {
        //<!--若需要开启日志，需要在初始化广告模块前调用-->
        SDKLogger.setDebug(BuildConfig.DEBUG)
        //<!--在Applicationz或广告调用前中初始化一次即可-->
        DexLoader.initIFLYADModule(App.context)
    }

    override fun createBannerAd(): BaseAd? {
        return IFlyBanner()
    }

    override fun createInsertAd(): BaseAd? {
        return IFlyInsert()
    }

    override fun createVideoAd(): BaseAd? {
        return IFlyNativeFullVideo()
    }

    override fun createDownloadAd(): BaseAd? {
        return null
    }

    fun startNativeVideoAd(context: Context, adPosition: String) {
        NativeFullVideoActivity.start(context, adPosition)
    }

    override fun destroy(context: Context) {

    }
}