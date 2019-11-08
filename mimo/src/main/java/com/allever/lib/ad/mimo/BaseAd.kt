package com.allever.lib.ad.mimo

import android.view.ViewGroup

abstract class BaseAd {
    abstract fun load(adPosition: String, container: ViewGroup, adListener: AdListener?)

    abstract fun show()
    abstract fun destroy()
}