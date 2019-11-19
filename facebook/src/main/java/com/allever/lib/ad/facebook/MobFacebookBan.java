package com.allever.lib.ad.facebook;

import android.content.Context;
import android.view.View;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.mob.core.MobBannerAd;
import com.mob.tool.Utils;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */
public class MobFacebookBan extends MobBannerAd {

    private com.facebook.ads.AdView facebookbanner = null;

    public MobFacebookBan(Context context, String pub) {
        super(context, pub);
    }

    @Override
    public void loadAd() {
        facebookbanner = new com.facebook.ads.AdView(mContext, mPub, AdSize.BANNER_320_50);
        facebookbanner.loadAd();
        facebookbanner.setAdListener(new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                if (mAdListener != null) {
                    Utils.printInfo("onError  " + mPub + "  " + adError.getErrorMessage() + "  " + adError.getErrorCode());

                    mAdListener.onAdBanFailedToLoad();
                    mAdListener = null;  //有些sdk可能一次请求回调多次失败或成功状态，那么我们应该避免这些问题
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (mAdListener != null) {
                    Utils.printInfo("suceess " + mPub);
                    mAdListener.onAdBanLoaded(MobFacebookBan.this);
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

        Utils.printInfo("loadAd " + getTag() + mPub);

    }

    @Override
    public View getBannerView() {
        return facebookbanner;
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
        return "facebook ban";
    }
}


