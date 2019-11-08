package com.allever.lib.ad.mimo

interface AdListener {
    fun onLoaded()
    fun onShowed()
    fun onDismiss()
    fun onFailed()
    fun playEnd() {}
    fun onStimulateSuccess() {}
}