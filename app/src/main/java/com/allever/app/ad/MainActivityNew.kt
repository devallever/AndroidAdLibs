package com.allever.app.ad

import android.os.Bundle
import com.allever.lib.common.app.BaseActivity
import com.mob.main.IMobAd
import com.mob.main.IMobAdListener
import com.mob.main.MobService
import com.mob.tool.Utils

class MainActivityNew: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //初始化广告
        MobService.getIns().startService(this, AdConstants.data, AdFactory())

//        //显示插屏
        MobService.getIns().loadInterstitalAd(this, "mobinter_req_a_ad2", object : IMobAdListener {
            override fun onAdLoaded(mobAd: IMobAd) {
                mobAd.showAd()
                Utils.printInfo("成功拿到广告")
            }

            override fun onAdFailedToLoad() {
                Utils.printInfo("广告轮询失败")
            }
        })


//        //插入banner条
        MobService.getIns().loadBanner(this, "mobbanner", object : IMobAdListener {
            override fun onAdLoaded(mobAd: IMobAd) {
                mobAd.showAd()
                Utils.printInfo("成功banner广告")
            }

            override fun onAdFailedToLoad() {
                Utils.printInfo("nanner广告轮询失败")
            }
        })
    }
}