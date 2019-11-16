package com.allever.lib.ad.ifly

import android.view.ViewGroup
import com.allever.lib.ad.AdListener
import com.allever.lib.ad.BaseAd
import com.allever.lib.common.app.App
import com.allever.lib.common.util.log
import com.iflytek.voiceads.IFLYVideoAd
import com.iflytek.voiceads.config.AdError
import com.iflytek.voiceads.config.AdKeys
import com.iflytek.voiceads.conn.VideoDataRef
import com.iflytek.voiceads.listener.IFLYVideoListener

class IFlyNativeFullVideo : BaseAd() {

    private var videoAd: IFLYVideoAd? = null
    private var videoADDataRef: VideoDataRef? = null
    private var adView: ViewGroup? = null

    override fun load(adPosition: String, container: ViewGroup?, adListener: AdListener?) {

    }

    override fun show() {
    }

    override fun loadAndShow(adPosition: String, container: ViewGroup?, adListener: AdListener?) {
        videoAd = IFLYVideoAd(
            App.context,
            adPosition,
            IFLYVideoAd.NATIVE_VIDEO_AD,
            object : IFLYVideoListener {
                override fun onAdFailed(error: AdError?) {
                    log("onAdFailed: ${error?.errorCode}: ${error?.errorDescription}")
                    adListener?.onFailed()
                }

                override fun onVideoReplay() {
                    log("onVideoReplay")
                }

                override fun onAdClick() {
                    log("onAdClick")
                    //当设置点击视频直接跳转,这里会回调
                    videoADDataRef?.onClick(adView)
                    adListener?.onClick()
                }

                override fun onVideoStart() {
                    log("onVideoStart")
                    adListener?.onShowed()
                }

                override fun onAdLoaded(dateRef: VideoDataRef?) {
                    log("onAdLoaded")
                    videoADDataRef = dateRef
                    videoAd?.cacheVideo()
                }

                override fun onVideoCached() {
                    log("onVideoCached")
                    adView = videoAd?.videoView
                    adView?.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    videoAd?.showAd()
                    container?.addView(adView)
                }

                override fun onCancel() {
                    log("onCancel")
                }

                override fun onAdPlayError() {
                    log("onAdPlayError")
                    adListener?.onFailed()
                }

                override fun onConfirm() {
                    log("onConfirm")
                }

                override fun onVideoComplete() {
                    log("onVideoComplete")
                    adListener?.playEnd()
                }

            })
        videoAd?.setParameter(AdKeys.APP_VER, "1.0")
        videoAd?.loadAd()
    }

    override fun destroy() {
        videoAd?.release()
        adView = null
        videoAd = null
    }

    fun onClick() {
        videoADDataRef?.onClick(adView)
    }
}