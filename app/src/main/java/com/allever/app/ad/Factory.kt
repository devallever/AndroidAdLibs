package com.allever.app.ad

import com.allever.lib.ad.AdBusiness
import com.allever.lib.ad.chain.IAdBusiness
import com.allever.lib.ad.chain.IAdBusinessFactory

class Factory: IAdBusinessFactory {
    override fun getAdBusiness(businessName: String): IAdBusiness? {
        return when(businessName) {
            AdBusiness.A -> {
                null
            }
            else -> {
                null
            }
        }
    }

}