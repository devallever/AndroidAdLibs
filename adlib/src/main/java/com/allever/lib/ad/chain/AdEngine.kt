package com.allever.lib.ad.chain

import android.view.ViewGroup
import com.allever.lib.ad.ADType
import com.allever.lib.common.util.log

class AdEngine(private val adName: String, adConfigBean: AdConfig.AdConfigBean?, private val container: ViewGroup?) {

    private var mAdType: String? = ""
    //广告链
    private var adChainList = mutableListOf<AdConfig.AdConfigBean.ChainBean>()

    init {
        mAdType = adConfigBean?.type
        adConfigBean?.chain?.let {
            adChainList.addAll(it)
        }
    }

    fun loadAd(adChainListener: AdChainListener?) {
        if (adChainList.size > 0) {
            log("加载 $adName")
            val chainBean = adChainList[0]
            adChainList.removeAt(0)
            val businessName = chainBean.business
            //根据business获取具体IAdBusiness
            val adPosition = chainBean.adPosition
            val adBusiness = AdChainHelper.nameAdBusinessMap[businessName!!]
            val ad = adBusiness?.createAd(mAdType!!)
            val listener = object : AdChainListener {
                override fun onLoaded(ad: IAd?) {
                    adChainListener?.onLoaded(ad)
                    log("加载【$businessName】$mAdType: $adPosition 成功")
                }

                override fun onFailed(msg: String) {
                    log("加载【$businessName】$mAdType: $adPosition 失败")
                    if (adChainList.isEmpty()) {
                        adChainListener?.onFailed(msg)
                    } else {
                        loadAd(adChainListener)
                    }
                }

                override fun onShowed() {
                    log("广告展示【$businessName】 $mAdType: $adPosition")
                }

                override fun onDismiss() {
                    log("广告消失【$businessName】 $mAdType: $adPosition")
                }
            }

            log("加载【$businessName】$mAdType - $adPosition")
            if (mAdType == ADType.BANNER) {
                ad?.loadAndShow(adPosition!!, container, listener)
            } else {
                ad?.load(adPosition!!, container, listener)
            }
        } else {
            log("广告链调用完了, 没有广告了")
            adChainListener?.onFailed("没有广告了")
        }
    }
}