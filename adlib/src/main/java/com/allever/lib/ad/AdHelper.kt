package com.allever.lib.ad

import android.content.Context

object AdHelper {

    private var mAdManager: AdManager? = null

    fun init(context: Context, adManager: AdManager, appId: String, appKey: String = "", appToken: String = "") {
        mAdManager = adManager
        mAdManager?.init(context, appId, appKey, appToken)
    }

    fun createAd(adType: ADType): BaseAd? {
        return mAdManager?.createAd(adType)
    }

    fun createBannerAd(): BaseAd? {
        return mAdManager?.createBannerAd()
    }

    fun createInsertAd(): BaseAd? {
        return mAdManager?.createInsertAd()
    }

    fun createVideoAd(): BaseAd? {
        return mAdManager?.createVideoAd()
    }
}
