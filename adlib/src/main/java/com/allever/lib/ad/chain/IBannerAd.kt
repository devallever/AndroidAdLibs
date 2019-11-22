package com.allever.lib.ad.chain

import android.view.ViewGroup
import com.allever.lib.ad.AdListener

abstract class IBannerAd : IAd() {
    override fun load(adPosition: String?, container: ViewGroup?, adListener: AdChainListener?) {

    }

    override fun show() {

    }

    abstract fun loadAndShow(adPosition: String?, container: ViewGroup?, adListener: AdListener?)

}