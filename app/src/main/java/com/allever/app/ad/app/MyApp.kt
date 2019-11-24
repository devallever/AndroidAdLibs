package com.allever.app.ad.app

import com.allever.app.ad.AdConstants
import com.allever.app.ad.AdFactory
import com.allever.lib.ad.chain.AdChainHelper
import com.allever.lib.common.app.App

class MyApp: App() {
    override fun onCreate() {
        super.onCreate()
        AdChainHelper.init(this, AdConstants.adData, AdFactory())
    }
}