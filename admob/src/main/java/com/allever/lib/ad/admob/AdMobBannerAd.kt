package com.allever.lib.ad.admob

import android.view.View
import android.view.ViewGroup
import com.allever.lib.ad.chain.AdChainHelper
import com.allever.lib.ad.chain.AdChainListener
import com.allever.lib.ad.chain.IAd
import com.allever.lib.common.app.App
import com.allever.lib.common.util.log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

class AdMobBannerAd: IAd() {
    private var mBannerView: AdView? = null
    override fun load(adPosition: String?, container: ViewGroup?, adListener: AdChainListener?) {

    }

    override fun show() {
    }

    override fun loadAndShow(
        adPosition: String?,
        container: ViewGroup?,
        adListener: AdChainListener?
    ) {
        mBannerView = AdView(App.context)
        mBannerView?.adSize = AdSize.SMART_BANNER
        mBannerView?.adUnitId = adPosition
        mBannerView?.adListener = object : com.google.android.gms.ads.AdListener() {
            override fun onAdFailedToLoad(i: Int) {
                super.onAdFailedToLoad(i)
                adListener?.onFailed("加载AdMob Banner 失败, 错误码： $i")
                log("加载 AdMob Banner 失败, 错误码： $i")
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                log("加载 AdMob Banner 成功")
                container?.addView(mBannerView)
                container?.visibility = View.VISIBLE
                adListener?.onLoaded(this@AdMobBannerAd)
            }
        }

        //加载请求
        val reqBuild = AdRequest.Builder()
        AdMobBusiness.testDevicesList.map {
            reqBuild.addTestDevice(it)
        }
        mBannerView?.loadAd(reqBuild.build())
    }

    override fun destroy() {
        mBannerView?.destroy()
    }

}