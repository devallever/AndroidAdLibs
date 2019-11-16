package com.allever.lib.ad.ifly

import android.view.ViewGroup
import com.allever.lib.ad.AdListener
import com.allever.lib.ad.BaseAd
import com.allever.lib.common.app.App
import com.allever.lib.common.util.log
import com.iflytek.voiceads.IFLYBannerAd
import com.iflytek.voiceads.config.AdError
import com.iflytek.voiceads.config.AdKeys
import com.iflytek.voiceads.listener.IFLYAdListener

class IFlyBanner: BaseAd() {

    private var bannerView: IFLYBannerAd? = null

    override fun load(adPosition: String, container: ViewGroup?, adListener: AdListener?) {

    }

    override fun show() {
    }

    override fun loadAndShow(adPosition: String, container: ViewGroup?, adListener: AdListener?) {
        //创建横幅广告，传入广告位ID
        bannerView = IFLYBannerAd.createBannerAd(App.context, adPosition)
        bannerView?.setParameter(AdKeys.APP_VER, "1.0")
        bannerView?.setParameter(AdKeys.DOWNLOAD_ALERT, true)
        //广告容器添加bannerView
        container?.addView(bannerView)
        //请求广告，添加监听器
        bannerView?.loadAd(object : IFLYAdListener {
            override fun onAdFailed(error: AdError?) {
                log("onAdFailed ${error?.errorCode}")
                adListener?.onFailed()
            }

            override fun onAdExposure() {
                log("onAdExposure")
            }

            override fun onCancel() {
                log("onCancel")
            }

            override fun onConfirm() {
                log("onConfirm")
            }

            override fun onAdClick() {
                log("onAdClick")
                adListener?.onClick()
            }

            override fun onAdClose() {
                log("onAdClose")
                adListener?.onDismiss()
            }

            override fun onAdReceive() {
                //
                log("onAdReceive")
                bannerView?.showAd()
                adListener?.onLoaded()
            }
        })
    }

    override fun destroy() {
        bannerView?.destroy()
    }
}