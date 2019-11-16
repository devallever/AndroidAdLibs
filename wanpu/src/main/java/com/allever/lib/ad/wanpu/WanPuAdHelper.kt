package com.allever.lib.ad.wanpu

import android.content.Context
import cn.waps.AppConnect
import com.allever.lib.ad.ADType
import com.allever.lib.ad.AdManager
import com.allever.lib.ad.BaseAd

object WanPuAdHelper: AdManager {
    override fun init(context: Context, appId: String, appKey: String, appToken: String) {
        //通过配置Manifest的方式调用功能
        AppConnect.getInstance(context)
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

    override fun createBannerAd(): BaseAd? {
        return WanPuBanner()
    }

    override fun createInsertAd(): BaseAd? {
        return WanPuCustomInsert()
    }

    override fun createVideoAd(): BaseAd? {
        return null
    }

    override fun createDownloadAd(): BaseAd? {
        return null
    }

    override fun destroy(context: Context) {
        AppConnect.getInstance(context).close()
    }
}