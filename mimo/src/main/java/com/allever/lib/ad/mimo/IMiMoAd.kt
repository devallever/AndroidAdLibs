package com.allever.lib.ad.mimo

import android.view.ViewGroup
import com.allever.lib.ad.chain.AdChainListener
import com.allever.lib.ad.chain.IAd
import com.miui.zeus.mimo.sdk.ad.IAdWorker
import com.xiaomi.ad.common.pojo.AdType

open class IMiMoAd(private val adType: AdType): IAd() {

    private var mAdWorker: IAdWorker? = null

    override fun load(adPosition: String?, container: ViewGroup?, adListener: AdChainListener?) {
        mAdWorker = MiMoBusiness.load(adType, adPosition!!, container, object : AdChainListener {
            override fun onLoaded(ad: IAd?) {
                adListener?.onLoaded(this@IMiMoAd)
            }

            override fun onFailed(msg: String) {
            }

            override fun onShowed() {
            }

            override fun onDismiss() {
            }

        })
        return
    }

    override fun show() {
        MiMoBusiness.show(mAdWorker)
    }

    override fun loadAndShow(
        adPosition: String?,
        container: ViewGroup?,
        adListener: AdChainListener?
    ) {
        mAdWorker = MiMoBusiness.loadAndShow(adType, adPosition!!, container, adListener)
    }

    override fun destroy() {
        MiMoBusiness.destroy(mAdWorker)
    }
}