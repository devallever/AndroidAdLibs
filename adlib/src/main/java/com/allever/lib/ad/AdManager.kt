package com.allever.lib.ad

import android.content.Context

interface AdManager {
    fun init(context: Context, appId: String)
}