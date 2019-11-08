package com.allever.lib.ad.mimo

import android.view.ViewGroup
import com.miui.zeus.mimo.sdk.ad.IRewardVideoAdWorker

class MiMoVideoAd: BaseAd() {
    private var iAdWorker: IRewardVideoAdWorker? = null
    override fun load(adPosition: String, container: ViewGroup, adListener: AdListener?) {
        iAdWorker = MiMoAdHelper.loadRewardVideo(adPosition, adListener)
    }

    override fun show() {
        MiMoAdHelper.showVideo(iAdWorker)
    }

    override fun destroy() {
        MiMoAdHelper.destroyVideo(iAdWorker)
    }
}