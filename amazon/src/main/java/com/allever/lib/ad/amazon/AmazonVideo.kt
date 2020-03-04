package com.allever.lib.ad.amazon

import android.view.ViewGroup
import com.allever.lib.ad.AdListener
import com.allever.lib.ad.BaseAd

class AmazonVideo : BaseAd(){
    override fun load(adPosition: String, container: ViewGroup?, adListener: AdListener?) {
        adListener?.onFailed()
    }

    override fun show() {
    }

    override fun loadAndShow(adPosition: String, container: ViewGroup?, adListener: AdListener?) {
        adListener?.onLoaded()
    }

    override fun destroy() {
    }
}