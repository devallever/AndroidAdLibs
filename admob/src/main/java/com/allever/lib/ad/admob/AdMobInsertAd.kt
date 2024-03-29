package com.allever.lib.ad.admob

import android.view.ViewGroup
import com.allever.lib.ad.chain.AdChainHelper
import com.allever.lib.ad.chain.AdChainListener
import com.allever.lib.ad.chain.IAd
import com.allever.lib.common.app.App
import com.allever.lib.common.util.log
import com.allever.lib.common.util.toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

class AdMobInsertAd: IAd() {

    private var interstitialAd: InterstitialAd? = null

    override fun load(adPosition: String?, container: ViewGroup?, adListener: AdChainListener?) {
        interstitialAd = InterstitialAd(App.context)
        interstitialAd?.adUnitId = adPosition
        interstitialAd?.adListener = object : com.google.android.gms.ads.AdListener() {
            override fun onAdClosed() {
                super.onAdClosed()
                adListener?.onDismiss()
            }

            override fun onAdFailedToLoad(i: Int) {
                super.onAdFailedToLoad(i)
                log("加载 AdMob Insert 失败, 错误码： $i")
                AdMobHelper.logError(i)
                adListener?.onFailed("加载 AdMob Insert 失败, 错误码： $i")
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                adListener?.onLoaded(this@AdMobInsertAd)
                log("加载 AdMob Insert 成功")
            }
        }
        //加载请求
        val reqBuild = AdRequest.Builder()
        for (device in AdMobBusiness.testDevicesList) {
            reqBuild.addTestDevice(device)
        }
        val adRequest = reqBuild.build()

        try {
            interstitialAd?.loadAd(adRequest)
        } catch (e: Exception) {
            e.printStackTrace()
            log("请求报错 AdMob Insert：${e.message}")
            adListener?.onFailed("请求报错 AdMob Insert：${e.message}")
        } catch (e: Error) {
            e.printStackTrace()
            log("请求报错 AdMob Insert：${e.message}")
            adListener?.onFailed("请求报错 AdMob Insert：${e.message}")
        }

    }

    override fun show() {
        interstitialAd?.show()
    }

    override fun loadAndShow(
        adPosition: String?,
        container: ViewGroup?,
        adListener: AdChainListener?
    ) {
    }

    override fun destroy() {
    }

    override fun onAdResume() {

    }

    override fun onAdPause() {

    }
}