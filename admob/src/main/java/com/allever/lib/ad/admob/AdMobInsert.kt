package com.allever.lib.ad.admob

import android.view.ViewGroup
import com.allever.lib.ad.AdListener
import com.allever.lib.ad.BaseAd
import com.allever.lib.common.app.App
import com.allever.lib.common.util.log
import com.allever.lib.common.util.toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

@Deprecated("")
class AdMobInsert: BaseAd() {
    private var interstitialAd: InterstitialAd? = null
    override fun load(adPosition: String, container: ViewGroup?, adListener: AdListener?) {
        interstitialAd = InterstitialAd(App.context)
        interstitialAd?.adUnitId = adPosition
        interstitialAd?.adListener = object : com.google.android.gms.ads.AdListener() {
            override fun onAdClosed() {
                super.onAdClosed()
                adListener?.onDismiss()
            }

            override fun onAdFailedToLoad(i: Int) {
                super.onAdFailedToLoad(i)
                adListener?.onFailed()
                log("加载 AdMob Insert 失败, 错误码： $i")
                toast("加载 AdMob Insert 失败, 错误码： $i")
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                adListener?.onLoaded()
                log("加载 AdMob Insert 成功")
            }
        }
        //加载请求
        val reqBuild = AdRequest.Builder()
        for (device in AdMobHelper.testDevicesList) {
            reqBuild.addTestDevice(device)
        }
        val adRequest = reqBuild.build()

        interstitialAd?.loadAd(adRequest)
    }

    override fun show() {
        interstitialAd?.show()
    }

    override fun loadAndShow(adPosition: String, container: ViewGroup?, adListener: AdListener?) {
    }

    override fun destroy() {
    }
}