package com.allever.lib.ad.wanpu

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.allever.lib.ad.ADType
import com.allever.lib.ad.AdHelper
import com.allever.lib.common.app.App
import com.allever.lib.common.app.BaseActivity

class WanpuMainActivity: BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wanpu_main)
        AdHelper.init(this, WanPuAdHelper, "")
        val bannerAd = AdHelper.createAd(ADType.BANNER)
        val bannerContainer = findViewById<LinearLayout>(R.id.bannerContainer)
        bannerAd?.loadAndShow("", bannerContainer, null)

        findViewById<View>(R.id.btnBanner).setOnClickListener(this)
        findViewById<View>(R.id.btnInsert).setOnClickListener(this)
        mHandler.postDelayed({
            AdHelper.createInsertAd()?.load("", null, null)
        }, 2000)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btnInsert -> {
                val insertAd = AdHelper.createAd(ADType.INSERT)
                insertAd?.loadAndShow("", null, null)
            }
            R.id.btnBanner -> {
                val bannerAd = AdHelper.createAd(ADType.BANNER)
                val bannerContainer = findViewById<LinearLayout>(R.id.bannerContainer)
                bannerContainer?.removeAllViews()
                bannerAd?.loadAndShow("", bannerContainer, null)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AdHelper.destroy(this)
    }


}