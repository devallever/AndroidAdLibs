package com.allever.lib.ad.facebook;

import android.content.Context;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.mob.core.MobInterstitialAd;
import com.mob.tool.Utils;

import java.util.List;

/**
 * Created by Administrator on 2016/8/25.
 */
public class MobFacebookInter extends MobInterstitialAd {


    public MobFacebookInter(Context context, String pub) {
        super(context, pub);
    }

    private InterstitialAd interstitialAd = null;

    @Override
    public void showAd() {
        if (interstitialAd != null) {
            interstitialAd.show();
        }
    }

    @Override
    public void loadAd() {
        Utils.printInfo("loadAd  " + mPub);
        interstitialAd = new InterstitialAd(mContext, mPub);
        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {
                if (mAdListener != null) {
                    Utils.printInfo("onError  " + mPub + "  " + adError.getErrorMessage() + "  " + adError.getErrorCode());
                    mAdListener.onAdFailedToLoad();
                    mAdListener = null;  //有些sdk可能一次请求回调多次失败或成功状态，那么我们应该避免这些问题
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (mAdListener != null) {
                    Utils.printInfo("onAdLoaded  " + mPub);
                    mAdListener.onAdLoaded(MobFacebookInter.this);
                    mAdListener = null;  //有些sdk可能一次请求回调多次失败或成功状态，那么我们应该避免这些问题
                }
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });
        interstitialAd.loadAd();
        Utils.printInfo("loadAd " + getTag() + mPub);

    }

    @Override
    public void initSdk() {
        //AdSettings.addTestDevice("e01bfd27a863893344db5c54611b261a");
//        AdSettings.addTestDevice("4a0a9085f69206042cfa95fe51bc082a");

        List<String> testDeviceList = getTestDeviceList();
        for (String testDevice : testDeviceList) {
            AdSettings.addTestDevice(testDevice);
        }

    }

    @Override
    public String getTag() {
        return "Facebook Inter";
    }
}
