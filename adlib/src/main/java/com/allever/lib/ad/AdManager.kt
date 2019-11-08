package com.allever.lib.ad

import android.content.Context

interface AdManager {

    fun init(context: Context, appId: String)

    fun createAd(adType: ADType): BaseAd?

    fun createBannerAd(): BaseAd?

    fun createInsertAd(): BaseAd?

    fun createVideoAd(): BaseAd?

    fun createDownloadAd(): BaseAd?

}