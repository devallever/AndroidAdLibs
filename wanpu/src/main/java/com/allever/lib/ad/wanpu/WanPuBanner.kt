package com.allever.lib.ad.wanpu

import android.view.ViewGroup
import android.widget.LinearLayout
import cn.waps.AppListener
import com.allever.lib.ad.AdListener
import com.allever.lib.ad.BaseAd
import com.allever.lib.ad.wanpu.WanPuAdHelper.mIns
import com.allever.lib.common.util.log
import java.lang.RuntimeException

class WanPuBanner: BaseAd() {
    override fun load(adPosition: String, container: ViewGroup?, adListener: AdListener?) {

    }

    override fun show() {
    }

    override fun loadAndShow(adPosition: String, container: ViewGroup?, adListener: AdListener?) {
        if (container is LinearLayout) {
            mIns.setBannerAdNoDataListener(object : AppListener() {
                override fun onBannerNoData() {
                    log("Banner无数据")
                }
            })
            mIns.showBannerAd(WanPuAdHelper.mContext, container)
        } else {
            throw RuntimeException("container 必须是LinearLayout")
        }
    }

    override fun destroy() {
    }
}