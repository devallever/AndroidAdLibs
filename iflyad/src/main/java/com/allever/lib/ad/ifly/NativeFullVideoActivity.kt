package com.allever.lib.ad.ifly

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.allever.lib.common.app.App
import com.allever.lib.common.app.BaseActivity
import com.allever.lib.common.util.log
import com.bumptech.glide.Glide
import com.iflytek.voiceads.IFLYVideoAd
import com.iflytek.voiceads.config.AdError
import com.iflytek.voiceads.config.AdKeys
import com.iflytek.voiceads.conn.VideoDataRef
import com.iflytek.voiceads.listener.IFLYVideoListener
import org.jetbrains.anko.toast

class NativeFullVideoActivity: BaseActivity(), View.OnClickListener{

    private var videoAd: IFLYVideoAd? = null
    private var videoADDataRef: VideoDataRef? = null
    private var adView: ViewGroup? = null

    private lateinit var mAdDetailContainer: ViewGroup
    private lateinit var mIvAdLogo: ImageView
    private lateinit var mTvAdTitle: TextView
    private lateinit var mTvAdDesc: TextView

    private var mPlayEnd = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_full_video)
        mAdDetailContainer = findViewById(R.id.adDetailContainer)
        mAdDetailContainer.setOnClickListener(this)
        mIvAdLogo = findViewById(R.id.adLogo)
        mTvAdTitle = findViewById(R.id.adTitle)
        mTvAdDesc = findViewById(R.id.adDetail)
        loadVideo()

    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.adDetailContainer -> {
                videoADDataRef?.onClick(adView)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        videoAd?.release()
        adView = null
        videoAd = null
    }

    private fun loadVideo() {
        val adContainer = findViewById<ViewGroup>(R.id.adContainer)
        val adPosition = intent?.getStringExtra(EXTRA_AD_POSITION)?:""
        videoAd = IFLYVideoAd(
            App.context,
            adPosition,
            IFLYVideoAd.NATIVE_VIDEO_AD,
            object : IFLYVideoListener {
                override fun onAdFailed(error: AdError?) {
                    log("onAdFailed: ${error?.errorCode}: ${error?.errorDescription}")
                    finish()
                }

                override fun onVideoReplay() {
                    log("onVideoReplay")
                }

                override fun onAdClick() {
                    log("onAdClick")
                    //当设置点击视频直接跳转,这里会回调
                }

                override fun onVideoStart() {
                    log("onVideoStart")
                }

                override fun onAdLoaded(dateRef: VideoDataRef?) {
                    log("onAdLoaded")
                    videoADDataRef = dateRef
                    videoAd?.cacheVideo()
                }

                override fun onVideoCached() {
                    log("onVideoCached")
                    adView = videoAd?.videoView
                    adView?.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    videoAd?.showAd()
                    adContainer?.addView(adView)

                    mAdDetailContainer.visibility = View.VISIBLE
                    var iconUrl = videoADDataRef?.iconUrl
                    if (TextUtils.isEmpty(iconUrl)) {
                        iconUrl = videoADDataRef?.imgUrl
                    }
                    val title = videoADDataRef?.title
                    val desc = videoADDataRef?.desc
                    mTvAdTitle.text = title
                    if (!TextUtils.isEmpty(desc)) {
                        mTvAdDesc.text = desc
                    }
                    Glide.with(this@NativeFullVideoActivity).load(iconUrl).into(mIvAdLogo)
                }

                override fun onCancel() {
                    log("onCancel")
                }

                override fun onAdPlayError() {
                    log("onAdPlayError")
                }

                override fun onConfirm() {
                    log("onConfirm")
                }

                override fun onVideoComplete() {
                    log("onVideoComplete")
                    mPlayEnd = true
                }

            })
        videoAd?.setParameter(AdKeys.APP_VER, "1.0")
        videoAd?.loadAd()
    }

    override fun onBackPressed() {
        if (!mPlayEnd) {
            toast("视频尚未结束")
            return
        }
        super.onBackPressed()
    }

    companion object {
        private const val EXTRA_AD_POSITION = "EXTRA_AD_POSITION"
        fun start(context: Context, adPosition: String) {
            val intent = Intent(context, NativeFullVideoActivity::class.java)
            intent.putExtra(EXTRA_AD_POSITION, adPosition)
            context.startActivity(intent)
        }
    }
}