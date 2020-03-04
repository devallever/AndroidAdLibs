package com.allever.app.ad

import android.os.Bundle
import com.allever.lib.ad.chain.AdChainHelper
import com.allever.lib.ad.chain.AdChainListener
import com.allever.lib.ad.chain.IAd
import com.allever.lib.common.app.BaseActivity
import kotlinx.android.synthetic.main.activity_admob_native.*

class AdmobNativeActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admob_native)

        loadNative()
    }

    private var mNativeAd: IAd? = null
    private fun loadNative() {
        AdChainHelper.loadAd(AdConstants.AD_NAME_NATIVE, nativeAdContainer, object : AdChainListener {
            override fun onLoaded(ad: IAd?) {
                mNativeAd = ad
                mNativeAd?.show()
            }

            override fun onFailed(msg: String) {
            }

            override fun onShowed() {
            }

            override fun onDismiss() {
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mNativeAd?.destroy()
    }
}