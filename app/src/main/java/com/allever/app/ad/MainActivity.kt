//package com.allever.app.ad
//
//import android.Manifest
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import android.view.ViewGroup
//import com.allever.lib.ad.ADType
//import com.allever.lib.ad.AdHelper
//import com.allever.lib.ad.AdListener
//import com.allever.lib.permission.PermissionListener
//import com.allever.lib.permission.PermissionManager
//
//class MainActivity : AppCompatActivity() {
//
//    private val mBanner = AdHelper.createBannerAd()
//    private val mInsert = AdHelper.createAd(ADType.INSERT)
//    private val mVideoAd = AdHelper.createAd(ADType.VIDEO)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        if (!PermissionManager.hasPermissions(
//            Manifest.permission.READ_PHONE_STATE,
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            )) {
//            PermissionManager.request(object : PermissionListener {
//                override fun onGranted(grantedList: MutableList<String>) {
//
//                }
//
//                override fun onDenied(deniedList: MutableList<String>) {
//                    finish()
//                }
//
//            },Manifest.permission.READ_PHONE_STATE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.ACCESS_FINE_LOCATION)
//        }
//
//        val bannerContainer = findViewById<ViewGroup>(R.id.bannerContainer)
//        mBanner?.loadAndShow(AdConstants.BANNER, bannerContainer, null)
//
//
//        findViewById<View>(R.id.btnShowInsert).setOnClickListener {
//            mInsert?.load(AdConstants.INSERT, window.decorView as ViewGroup, object : AdListener {
//                override fun onLoaded() {
//                    mInsert.show()
//                }
//
//                override fun onShowed() {
//                }
//
//                override fun onDismiss() {
//                }
//
//                override fun onFailed() {
//                }
//
//            })
//        }
//
//        findViewById<View>(R.id.btnShowVideo).setOnClickListener {
//            mVideoAd?.load(AdConstants.VIDEO, null, object : AdListener {
//                override fun onLoaded() {
//                    mVideoAd.show()
//                }
//
//                override fun onShowed() {
//                }
//
//                override fun onDismiss() {
//                }
//
//                override fun onFailed() {
//                }
//
//            })
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mBanner?.destroy()
//        mInsert?.destroy()
//        mVideoAd?.destroy()
//    }
//}
