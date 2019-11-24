package com.allever.app.ad

import android.os.Bundle
import android.view.ViewGroup
import com.allever.lib.ad.chain.AdChainHelper
import com.allever.lib.ad.chain.AdChainListener
import com.allever.lib.ad.chain.IAd
import com.allever.lib.common.app.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivityNew: BaseActivity() {
    private var mBannerAd: IAd? = null
    private var mInsertAd: IAd? = null
    private var mVideoAd: IAd? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bannerContainer = findViewById<ViewGroup>(R.id.bannerContainer)

        btnBanner.setOnClickListener {
            mBannerAd?.destroy()
            mBannerAd = null
            AdChainHelper.loadAd(AdConstants.AD_NAME_BANNER, bannerContainer, object : AdChainListener {
                override fun onLoaded(ad: IAd?) {
                    mBannerAd = ad
                }
                override fun onFailed(msg: String) {}
                override fun onShowed() {}
                override fun onDismiss() {}

            })

        }
        btnInsert.setOnClickListener {
            mInsertAd?.destroy()
            mInsertAd = null
            AdChainHelper.loadAd(AdConstants.AD_NAME_INSERT, null, object : AdChainListener {
                override fun onLoaded(ad: IAd?) {
                    mInsertAd = ad
                    mInsertAd?.show()
                }
                override fun onFailed(msg: String) {}
                override fun onShowed() {}
                override fun onDismiss() {}

            })

        }
        btnVideo.setOnClickListener {
            mVideoAd?.destroy()
            mVideoAd = null
            AdChainHelper.loadAd(AdConstants.AD_NAME_VIDEO, null, object : AdChainListener {
                override fun onLoaded(ad: IAd?) {
                    mVideoAd = ad
                    mVideoAd?.show()
                }
                override fun onFailed(msg: String) {}
                override fun onShowed() {}
                override fun onDismiss() {}

            })
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mBannerAd?.destroy()
        mInsertAd?.destroy()
        mVideoAd?.destroy()
    }
}