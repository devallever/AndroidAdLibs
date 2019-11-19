package com.allever.app.ad

import android.os.Bundle
import android.view.ViewGroup
import com.allever.lib.ad.AdHelper
import com.allever.lib.ad.AdListener
import com.allever.lib.common.app.BaseActivity
import com.google.android.gms.ads.MobileAds
import com.mob.main.IMobAd
import com.mob.main.IMobAdListener
import com.mob.main.MobService
import com.mob.tool.Utils

class MainActivityNew: BaseActivity() {
    private val mBannerAd = AdHelper.createBannerAd()
    private val mInsertAd = AdHelper.createInsertAd()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bannerContainer = findViewById<ViewGroup>(R.id.bannerContainer)
        mBannerAd?.loadAndShow(AdConstants.ADMOB_BANNER_PUB, bannerContainer, null)

        mInsertAd?.load(AdConstants.ADMOB_INSERT_PUB, null, object : AdListener {
            override fun onLoaded() {
                mInsertAd.show()
            }

            override fun onShowed() {
            }

            override fun onDismiss() {
            }

            override fun onFailed() {
            }

        })

//        //初始化广告
//        MobService.getIns().startService(this, AdConstants.data, AdFactory())
//
////        //显示插屏
//        MobService.getIns().loadInterstitalAd(this, "mobinter_req_a_ad2", object : IMobAdListener {
//            override fun onAdLoaded(mobAd: IMobAd) {
//                mobAd.showAd()
//                Utils.printInfo("成功拿到广告")
//            }
//
//            override fun onAdFailedToLoad() {
//                Utils.printInfo("广告轮询失败")
//            }
//        })
//
//
////        //插入banner条
//        MobService.getIns().loadBanner(this, "mobbanner", object : IMobAdListener {
//            override fun onAdLoaded(mobAd: IMobAd) {
//                mobAd.showAd()
//                Utils.printInfo("成功banner广告")
//            }
//
//            override fun onAdFailedToLoad() {
//                Utils.printInfo("nanner广告轮询失败")
//            }
//        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mBannerAd?.destroy()
    }
}