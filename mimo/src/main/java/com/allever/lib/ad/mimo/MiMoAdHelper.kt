package com.allever.lib.ad.mimo

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.allever.lib.ad.mimo.MiMoConstants.XIAO_MI_APP_KEY
import com.allever.lib.ad.mimo.MiMoConstants.XIAO_MI_APP_TOKEN
import com.allever.lib.common.app.App
import com.allever.lib.common.util.log
import com.miui.zeus.mimo.sdk.MimoSdk
import com.miui.zeus.mimo.sdk.ad.AdWorkerFactory
import com.miui.zeus.mimo.sdk.ad.IAdWorker
import com.miui.zeus.mimo.sdk.ad.IRewardVideoAdWorker
import com.miui.zeus.mimo.sdk.api.IMimoSdkListener
import com.miui.zeus.mimo.sdk.listener.MimoAdListener
import com.miui.zeus.mimo.sdk.listener.MimoRewardVideoListener
import com.xiaomi.ad.common.pojo.AdType

object MiMoAdHelper {

    fun init(context: Context, appId: String) {
        // 如果担心sdk自升级会影响开发者自身app的稳定性可以关闭，
        // 但是这也意味着您必须得重新发版才能使用最新版本的sdk, 建议开启自升级
        MimoSdk.setEnableUpdate(false)

        MimoSdk.setDebug(BuildConfig.DEBUG) // 正式上线时候务必关闭debug模式
//        MimoSdk.setStaging(BuildConfig.DEBUG) // 正式上线时候务必关闭stage模式

        // 如需要在本地预置插件,请在assets目录下添加mimo_asset.apk;
        MimoSdk.init(context, appId, XIAO_MI_APP_KEY, XIAO_MI_APP_TOKEN, object :
            IMimoSdkListener {
            override fun onSdkInitSuccess() {
                log("初始化米盟成功")
            }

            override fun onSdkInitFailed() {
                log("初始化米盟失败")
            }
        })
    }

    fun loadEncourageDownload(adPosition: String, adListener: AdListener?): IAdWorker {
        val adWorker =
            AdWorkerFactory.getAdWorker(App.context, null, object : MimoAdListener {
                override fun onAdPresent() {
                    log("激励下载 onAdPresent")
                    adListener?.onShowed()
                }

                override fun onAdClick() {
                    log("激励下载 onAdClick")
                }

                override fun onAdDismissed() {
                    log("激励下载 onAdDismissed")
                    adListener?.onDismiss()
                }

                override fun onAdFailed(s: String) {
                    log("激励下载 请求失败 : $s")
                    adListener?.onFailed()
                }

                override fun onAdLoaded(i: Int) {
                    log("激励下载 onAdLoaded : $i")
                    adListener?.onLoaded()
                }

                override fun onStimulateSuccess() {
                    log("激励下载  onStimulateSuccess")
                    adListener?.onStimulateSuccess()
                }
            }, AdType.AD_STIMULATE_DOWNLOAD)
        adWorker.load(adPosition)
        return adWorker
    }

    fun loadInsert(
        adPosition: String, container: ViewGroup,
        adListener: AdListener?
    ): IAdWorker? {
        val worker = createAdWorker(AdType.AD_INTERSTITIAL, container, adListener)
        try {
            worker?.load(adPosition)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return worker
    }

    fun show(adWorker: IAdWorker?) {
        try {
            adWorker?.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadAndShowBanner(
        adPosition: String,
        container: ViewGroup,
        adListener: AdListener?
    ): IAdWorker? {
        val worker = createAdWorker(AdType.AD_BANNER, container, adListener)
        try {
            worker?.loadAndShow(adPosition)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return worker
    }

    fun loadAndShowSplash(
        adPosition: String,
        container: ViewGroup,
        adListener: AdListener?
    ): IAdWorker? {
        val worker = createAdWorker(AdType.AD_SPLASH, container, adListener)
        try {
            worker?.loadAndShow(adPosition)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return worker
    }

    fun loadRewardVideo(adPosition: String, adListener: AdListener?): IRewardVideoAdWorker? {
        var adWorker: IRewardVideoAdWorker? = null
        try {
            adWorker = AdWorkerFactory
                .getRewardVideoAdWorker(
                    App.context,
                    adPosition,
                    AdType.AD_REWARDED_VIDEO
                )
            adWorker.setListener(object : MimoRewardVideoListener {
                override fun onAdFailed(p0: String?) {
                    log("激励视频 onAdFailed: $p0")
                    adListener?.onFailed()
                }

                override fun onAdDismissed() {
                    log("激励视频 onAdDismissed")
                    adListener?.onDismiss()
                }

                override fun onAdPresent() {
                    log("激励视频 onAdPresent")
                    adListener?.onShowed()
                }

                override fun onAdClick() {
                    log("激励视频 onAdClick")
                }

                override fun onVideoPause() {
                    log("激励视频 onVideoPause")
                }

                override fun onVideoStart() {
                    log("激励视频 onVideoStart")
                }

                override fun onVideoComplete() {
                    log("激励视频 onVideoComplete")
                    adListener?.playEnd()
                }

                override fun onStimulateSuccess() {
                    log("激励视频 onStimulateSuccess")
                }

                override fun onAdLoaded(p0: Int) {
                    log("激励视频 onAdLoaded")
                    adListener?.onLoaded()
                }

            })
            adWorker.load()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return adWorker
    }

    fun showVideo(iRewardVideoAdWorker: IRewardVideoAdWorker?) {
        try {
            iRewardVideoAdWorker?.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun destroyVideo(iRewardVideoAdWorker: IRewardVideoAdWorker?) {
        try {
            iRewardVideoAdWorker?.recycle()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun destroy(adWorker: IAdWorker?) {
        try {
            adWorker?.recycle()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Deprecated("")
    fun requestAdPermission(activity: Activity, msg: String) {
        // 如果api >= 23 需要显式申请权限
        if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.READ_PHONE_STATE
            ) !== PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) !== PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.READ_CONTACTS
            ) !== PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.INTERNET
            ) !== PackageManager.PERMISSION_GRANTED
        ) {

            AlertDialog.Builder(activity)
                .setMessage(msg)
                .setPositiveButton("同意") { dialog, which ->
                    dialog.dismiss()
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.READ_CONTACTS
                        ),
                        0
                    )
                }
                .setNegativeButton("拒绝") { dialog, which ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }
    }

    private fun createAdWorker(
        adType: AdType,
        container: ViewGroup,
        adListener: AdListener?
    ): IAdWorker? {
        var worker: IAdWorker? = null
        try {
            worker = AdWorkerFactory.getAdWorker(App.context, container, object : MimoAdListener {
                override fun onAdLoaded(p0: Int) {
                    log("onAdLoaded")
                    adListener?.onLoaded()
                }

                override fun onAdPresent() {
                    log("onAdPresent")
                    adListener?.onShowed()
                }

                override fun onAdFailed(msg: String?) {
                    log("onAdFailed $msg")
                    adListener?.onFailed()
                }

                override fun onAdDismissed() {
                    log("onAdDismissed")
                }

                override fun onAdClick() {
                    log("onAdClick")
                }

                override fun onStimulateSuccess() {
                    log("onStimulateSuccess")
                }
            }, adType)
        } catch (e: Exception) {
            e.printStackTrace()
            adListener?.onFailed()
        }
        return worker
    }
}
