package com.allever.lib.ad.chain

import android.content.Context
import com.allever.lib.ad.ADType

abstract class IAdBusiness {

    abstract fun init(context: Context, appId: String, appKey: String, appToken: String)

    open fun createAd(adType: String): IAd? {
        return when (adType) {
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

    abstract fun createBannerAd(): IAd?

    abstract fun createInsertAd(): IAd?

    abstract fun createVideoAd(): IAd?

    abstract fun createDownloadAd(): IAd?

    abstract fun destroy(context: Context)

}