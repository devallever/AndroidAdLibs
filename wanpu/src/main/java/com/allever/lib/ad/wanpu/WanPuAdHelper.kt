package com.allever.lib.ad.wanpu

import android.annotation.SuppressLint
import android.content.Context
import cn.waps.AppConnect
import com.allever.lib.ad.AdManager
import com.allever.lib.ad.BaseAd

@SuppressLint("StaticFieldLeak")
object WanPuAdHelper: AdManager() {
    lateinit var mIns: AppConnect
    var mContext: Context? = null
    override fun init(context: Context, appId: String, appKey: String, appToken: String) {
        //通过配置Manifest的方式调用功能
        mIns = AppConnect.getInstance(context)
        mContext = context
    }

    override fun createBannerAd(): BaseAd? {
        return WanPuBanner()
    }

    override fun createInsertAd(): BaseAd? {
        return WanPuCustomInsert()
    }

    override fun createVideoAd(): BaseAd? {
        return null
    }

    override fun createDownloadAd(): BaseAd? {
        return null
    }

    override fun destroy(context: Context) {
        mIns.close()
        mContext = null
    }
}