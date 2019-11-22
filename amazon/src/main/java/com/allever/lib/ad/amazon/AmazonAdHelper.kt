package com.allever.lib.ad.amazon

import android.content.Context
import com.allever.lib.ad.ADType
import com.allever.lib.ad.AdManager
import com.allever.lib.ad.BaseAd
import com.allever.lib.common.util.loge
import com.amazon.device.ads.AdRegistration

object AmazonAdHelper: AdManager() {
    /***
     * appId 就是appkey
     */
    override fun init(context: Context, appId: String, appKey: String, appToken: String) {
        // For debugging purposes enable logging, but disable for production builds.
        AdRegistration.enableLogging(BuildConfig.DEBUG)
        // For debugging purposes flag all ad requests as tests, but set to false for production builds.
        AdRegistration.enableTesting(BuildConfig.DEBUG)
        try {
            AdRegistration.setAppKey(appId)
        } catch (e: IllegalArgumentException) {
            loge("IllegalArgumentException thrown: $e")
            return
        }
    }

    override fun createBannerAd(): BaseAd? = AmazonBanner()

    override fun createInsertAd(): BaseAd? = AmazonInsert()

    override fun createVideoAd(): BaseAd? = AmazonVideo()

    override fun createDownloadAd(): BaseAd? = null

    override fun destroy(context: Context) {
    }
}