package com.allever.lib.ad.admob

import android.content.Context
import com.allever.lib.ad.ADType
import com.allever.lib.ad.AdManager
import com.allever.lib.ad.BaseAd
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds

object AdMobHelper: AdManager {
    var testDevicesList = mutableListOf<String>()
    override fun init(context: Context, appId: String, appKey: String, appToken: String) {
        MobileAds.initialize(context) {

        }
        testDevicesList.add("811A5A5DA1BF1E2FC9EE39041EC322FF")
        testDevicesList.add("1621DB3C172AE6711BA840F4AEF6EF48")
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
        return AdMobBanner()
    }

    override fun createInsertAd(): BaseAd? {
        return AdMobInsert()
    }

    override fun createVideoAd(): BaseAd? = AdMobVideo()

    override fun createDownloadAd(): BaseAd? = null

    override fun destroy(context: Context) {
    }

    fun createAdRequest(): AdRequest {
        //加载请求
        val reqBuild = AdRequest.Builder()
        for (device in testDevicesList) {
            reqBuild.addTestDevice(device)
        }
        return reqBuild.build()
    }

}