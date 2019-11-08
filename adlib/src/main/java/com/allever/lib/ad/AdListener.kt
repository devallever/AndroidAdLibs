package com.allever.lib.ad

interface AdListener {
    fun onLoaded()

    fun onShowed()

    fun onDismiss()

    fun onFailed()

    fun playEnd() {}

    fun onStimulateSuccess() {}

    fun onClick() {}
}