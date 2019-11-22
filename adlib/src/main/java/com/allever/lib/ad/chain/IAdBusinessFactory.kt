package com.allever.lib.ad.chain

interface IAdBusinessFactory {

    fun getAdBusiness(businessName: String): IAdBusiness?

}