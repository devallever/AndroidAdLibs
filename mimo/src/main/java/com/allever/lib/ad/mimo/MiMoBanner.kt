package com.allever.lib.ad.mimo

import android.view.ViewGroup
import com.allever.lib.ad.AdListener
import com.xiaomi.ad.common.pojo.AdType

class MiMoBanner : MiMoAd(AdType.AD_BANNER) {
    override fun load(adPosition: String, container: ViewGroup?, adListener: AdListener?) {
        loadAndShow(adPosition, container, adListener)
    }
}