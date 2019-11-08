package com.allever.lib.ad

import android.view.ViewGroup
import com.allever.lib.ad.AdListener

abstract class BaseAd {

    abstract fun load(adPosition: String, container: ViewGroup, adListener: AdListener?)

    abstract fun show()

    abstract fun destroy()
}