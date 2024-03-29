package com.allever.lib.ad.admob_lite;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.mob.core.MobInterstitialAd;
import com.mob.bean.Model;
import com.mob.tool.Utils;

import java.util.List;

/**
 * Created by Administrator on 2016/8/25.
 */
public class MobAdmobInter extends MobInterstitialAd {

    public MobAdmobInter(Context context, String pub) {
        super(context, pub);
    }

    private InterstitialAd interstitialAd = null;
    private AdRequest adRequest = null;

    @Override
    public void loadAd() {
        interstitialAd = new InterstitialAd(mContext);
        interstitialAd.setAdUnitId(mPub);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                if (mAdListener != null) {
                    Utils.printInfo("loadAdFaild Admob");
                    mAdListener.onAdFailedToLoad();
                    mAdListener = null;  //有些sdk可能一次请求回调多次失败或成功状态，那么我们应该避免这些问题
                }
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (mAdListener != null) {
                    Utils.printInfo("onAdLoaded Admob");
                    mAdListener.onAdLoaded(MobAdmobInter.this);
                    mAdListener = null;  //有些sdk可能一次请求回调多次失败或成功状态，那么我们应该避免这些问题
                }
            }
        });
        //加载请求
        //AdRequest.Builder reqBuild = new AdRequest.Builder().addTestDevice("F711E9F86475CB61F3477AB351BC65B2");
        AdRequest.Builder reqBuild = new AdRequest.Builder();
        List<String> testDevice = getTestDeviceList();
        for (String deviceStr : testDevice) {
            reqBuild.addTestDevice(deviceStr);
        }
        for (String device : Model.getInstance().getTestDevice()) {
            reqBuild.addTestDevice(device);
        }
        adRequest = reqBuild.build();

        interstitialAd.loadAd(adRequest);
        Utils.printInfo("loadAd " + getTag() + mPub);

    }

    @Override
    public void initSdk() {

    }

    @Override
    public void showAd() {
        if (interstitialAd != null) {
            interstitialAd.show();
        }
    }

    @Override
    public String getTag() {
        return "Admob Inter";
    }
}
