package com.allever.lib.ad.wanpu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import cn.waps.AdInfo
import cn.waps.AppConnect
import com.allever.lib.ad.AdListener
import com.allever.lib.ad.BaseAd
import com.allever.lib.ad.wanpu.WanPuAdHelper.mIns
import com.allever.lib.common.app.App
import com.allever.lib.common.util.ActivityCollector
import com.bumptech.glide.Glide

class WanPuCustomInsert: BaseAd() {

    private var mInsertDialog: AlertDialog? = null
    private var mAdInfo: AdInfo? = null

    override fun load(adPosition: String, container: ViewGroup?, adListener: AdListener?) {
        mAdInfo = mIns.adInfo
        if (mAdInfo != null) {
            adListener?.onLoaded()
        } else {
            adListener?.onFailed()
        }
    }

    override fun show() {
        mAdInfo?: return
        val view = LayoutInflater.from(App.context).inflate(R.layout.dialog_ad_insert, null, false)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvName = view.findViewById<TextView>(R.id.tvAppName)
        val tvVersion = view.findViewById<TextView>(R.id.tvAppVersion)
        val tvSize = view.findViewById<TextView>(R.id.tvAppSize)
        val tvDetail = view.findViewById<TextView>(R.id.tvAppDetail)
        val ivLogo = view.findViewById<ImageView>(R.id.ivLogo)
        tvTitle.text = mAdInfo?.adText
        tvName.text = mAdInfo?.adName
        tvVersion.text = mAdInfo?.version
        tvSize.text = mAdInfo?.filesize
        tvDetail.text = mAdInfo?.description
        Glide.with(App.context).load(mAdInfo?.adIcon).into(ivLogo)
        view.findViewById<View>(R.id.adContainer).setOnClickListener {
            mIns.downloadAd(App.context, mAdInfo?.adId)
            mInsertDialog?.dismiss()
        }
        view.findViewById<View>(R.id.detailContentContainer).setOnClickListener {
            mIns.downloadAd(App.context, mAdInfo?.adId)
            mInsertDialog?.dismiss()
        }
        view.findViewById<View>(R.id.btnDownloadBottom).setOnClickListener {
            mIns.downloadAd(App.context, mAdInfo?.adId)
            mInsertDialog?.dismiss()
        }
        view.findViewById<View>(R.id.tvClose).setOnClickListener {
            mInsertDialog?.dismiss()
        }
        mInsertDialog = AlertDialog.Builder(ActivityCollector.getTopActivity()?:return)
            .setView(view)
            .setCancelable(false)
            .create()
        mInsertDialog?.show()
    }

    override fun loadAndShow(adPosition: String, container: ViewGroup?, adListener: AdListener?) {
        load(adPosition, container, adListener)
        show()
    }

    override fun destroy() {
        mInsertDialog = null
        mAdInfo = null
    }
}