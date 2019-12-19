package com.allever.lib.ad.admob

import android.view.ViewGroup
import com.allever.lib.ad.chain.AdChainListener
import com.allever.lib.ad.chain.IAd
import com.allever.lib.common.app.App
import com.allever.lib.common.util.ActivityCollector
import com.allever.lib.common.util.log
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class AdMobVideoAd: IAd() {

    private var rewardedAd: RewardedAd? = null
    private var mAdListener: AdChainListener? = null

    override fun load(adPosition: String?, container: ViewGroup?, adListener: AdChainListener?) {
        rewardedAd = RewardedAd(App.context, adPosition)
        try {
            rewardedAd?.loadAd(
                AdMobHelper.createAdRequest(),
                object : RewardedAdLoadCallback() {
                    override fun onRewardedAdLoaded() {
                        log("AdMob 激励视频 加载成功")
                        adListener?.onLoaded(this@AdMobVideoAd)
                    }

                    override fun onRewardedAdFailedToLoad(errorCode: Int) {
                        log("AdMob 激励视频 加载失败： $errorCode")
                        AdMobHelper.logError(errorCode)
                        adListener?.onFailed("AdMob 激励视频 加载失败： $errorCode")
                    }
                })
        } catch (e: Exception) {
            e.printStackTrace()
            log("请求报错 AdMob Video：${e.message}")
            adListener?.onFailed("请求报错 AdMob Video：${e.message}")
        } catch (e: Error) {
            e.printStackTrace()
            log("请求报错 AdMob Video：${e.message}")
            adListener?.onFailed("请求报错 AdMob Video：${e.message}")
        }
        mAdListener = adListener
    }

    override fun show() {
        val adCallback = object : RewardedAdCallback() {
            override fun onRewardedAdOpened() {
                // Ad opened.
                log("AdMob 激励视频 Ad onRewardedAdOpened. ")
                mAdListener?.onShowed()
            }

            override fun onRewardedAdClosed() {
                // Ad closed.
                log("AdMob 激励视频 Ad closed. ")
                mAdListener?.onDismiss()
            }

            override fun onUserEarnedReward(rewardItem: RewardItem) {
                // User earned reward.
                log("AdMob 激励视频 onUserEarnedReward ")
            }

            override fun onRewardedAdFailedToShow(errorCode: Int) {
                // Ad failed to display
                log("AdMob 激励视频 onRewardedAdFailedToShow $errorCode")
            }
        }
        rewardedAd?.show(ActivityCollector.getTopActivity(), adCallback)
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