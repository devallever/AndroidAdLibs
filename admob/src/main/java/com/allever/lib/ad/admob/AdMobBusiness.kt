package com.allever.lib.ad.admob

import android.content.Context
import com.allever.lib.ad.chain.IAd
import com.allever.lib.ad.chain.IAdBusiness
import com.google.android.gms.ads.MobileAds

object AdMobBusiness : IAdBusiness() {

    var testDevicesList = mutableListOf<String>()

    override fun init(context: Context, appId: String, appKey: String, appToken: String) {
        MobileAds.initialize(context) {

        }
//        testDevicesList.add("811A5A5DA1BF1E2FC9EE39041EC322FF")
//        testDevicesList.add("1621DB3C172AE6711BA840F4AEF6EF48")
    }

    override fun createBannerAd(): IAd? = AdMobBannerAd()

    override fun createInsertAd(): IAd? = AdMobInsertAd()

    override fun createVideoAd(): IAd? = AdMobVideoAd()

    override fun createDownloadAd(): IAd? = null

    override fun destroy(context: Context) {
    }

}