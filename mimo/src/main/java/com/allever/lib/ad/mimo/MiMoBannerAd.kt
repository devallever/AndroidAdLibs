package com.allever.lib.ad.mimo

import android.view.ViewGroup
import com.miui.zeus.mimo.sdk.ad.IAdWorker

class MiMoBannerAd: BaseAd() {
    private var iAdWorker: IAdWorker? = null
    override fun load(adPosition: String, container: ViewGroup, adListener: AdListener?) {
        iAdWorker = MiMoAdHelper.loadAndShowBanner(adPosition, container, adListener)
    }

    override fun show() {
        MiMoAdHelper.show(iAdWorker)
    }

    override fun destroy() {
        MiMoAdHelper.destroy(iAdWorker)
    }
}