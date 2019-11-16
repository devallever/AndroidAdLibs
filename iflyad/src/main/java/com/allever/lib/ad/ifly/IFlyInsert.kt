package com.allever.lib.ad.ifly

import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.allever.lib.ad.AdListener
import com.allever.lib.ad.BaseAd
import com.allever.lib.common.app.App
import com.allever.lib.common.util.ActivityCollector
import com.allever.lib.common.util.log
import com.iflytek.voiceads.IFLYInterstitialAd
import com.iflytek.voiceads.config.AdError
import com.iflytek.voiceads.config.AdKeys
import com.iflytek.voiceads.listener.IFLYAdListener

class IFlyInsert: BaseAd() {

    private var interstitialView: IFLYInterstitialAd? = null
    private var mAdInsertDialog: AlertDialog? = null

    override fun load(adPosition: String, container: ViewGroup?, adListener: AdListener?) {
        interstitialView = IFLYInterstitialAd.createInterstitialAd(App.context, adPosition)
        interstitialView?.setParameter(AdKeys.APP_VER, "1.0")
        interstitialView?.setParameter(AdKeys.DOWNLOAD_ALERT, true)
        //广告容器添加bannerView
//        interstitialAdLayout.addView(interstitialView);
        //请求广告，添加监听器
        interstitialView?.loadAd(object : IFLYAdListener {
            override fun onAdFailed(error: AdError?) {
                log("onAdFailed ${error?.errorCode}")
                adListener?.onFailed()
            }

            override fun onAdExposure() {
                log("onAdExposure")
            }

            override fun onCancel() {
                mAdInsertDialog?.dismiss()
                log("onCancel")
            }

            //下载确认
            override fun onConfirm() {
                mAdInsertDialog?.dismiss()
                log("onConfirm")
            }

            //点击
            override fun onAdClick() {
                mAdInsertDialog?.dismiss()
                log("onAdClick")
                adListener?.onClick()
            }

            override fun onAdClose() {
                mAdInsertDialog?.dismiss()
                log("onAdClose")
                adListener?.onDismiss()
            }

            override fun onAdReceive() {
                log("onAdReceive")
                adListener?.onLoaded()
            }

        })
    }

    override fun show() {
        mAdInsertDialog = AlertDialog.Builder(ActivityCollector.getTopActivity()?:return)
            .setCancelable(true)
            .setView(interstitialView)
            .create()
        interstitialView?.showAd()
        mAdInsertDialog?.show()
    }

    override fun loadAndShow(adPosition: String, container: ViewGroup?, adListener: AdListener?) {
    }

    override fun destroy() {
        interstitialView?.destroy()
        mAdInsertDialog?.dismiss()
    }
}