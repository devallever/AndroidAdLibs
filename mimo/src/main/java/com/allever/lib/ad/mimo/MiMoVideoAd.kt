package com.allever.lib.ad.mimo

import android.view.ViewGroup
import com.allever.lib.ad.chain.AdChainListener
import com.allever.lib.ad.chain.IAd
import com.miui.zeus.mimo.sdk.ad.IRewardVideoAdWorker

class MiMoVideoAd: IAd() {

    private var mAdWorker: IRewardVideoAdWorker? = null

    override fun load(adPosition: String?, container: ViewGroup?, adListener: AdChainListener?) {
        mAdWorker = MiMoBusiness.loadRewardVideo(adPosition!!, adListener)
    }

    override fun show() {
        MiMoBusiness.showVideo(mAdWorker)
    }

    override fun loadAndShow(
        adPosition: String?,
        container: ViewGroup?,
        adListener: AdChainListener?
    ) {
    }

    override fun destroy() {
        MiMoBusiness.destroyVideo(mAdWorker)
    }
}