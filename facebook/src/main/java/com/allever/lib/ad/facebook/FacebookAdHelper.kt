package com.allever.lib.ad.facebook

import android.content.Context
import com.allever.lib.ad.ADType
import com.allever.lib.ad.AdManager
import com.allever.lib.ad.BaseAd

object FacebookAdHelper: AdManager {
    override fun init(context: Context, appId: String, appKey: String, appToken: String) {

    }

    override fun createAd(adType: ADType): BaseAd? {
        return when(adType) {
            ADType.BANNER -> {
                return createBannerAd()
            }
            ADType.INSERT -> {
                return createInsertAd()
            }
            ADType.VIDEO -> {
                return createVideoAd()
            }
            ADType.DOWNLOAD -> {
                return createDownloadAd()
            }

            else -> null
        }
    }

    override fun createBannerAd(): BaseAd? = FacebookBanner()

    override fun createInsertAd(): BaseAd?  = FacebookInsert()

    override fun createVideoAd(): BaseAd? = null

    override fun createDownloadAd(): BaseAd?  = null

    override fun destroy(context: Context) {
    }
}