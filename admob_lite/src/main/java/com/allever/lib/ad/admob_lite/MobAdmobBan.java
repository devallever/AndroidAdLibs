package com.allever.lib.ad.admob_lite;

import android.content.Context;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.mob.core.MobBannerAd;
import com.mob.bean.Model;
import com.mob.tool.Utils;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */
public class MobAdmobBan extends MobBannerAd {

    private AdView admobbanner = null;

    public MobAdmobBan(Context context, String pub) {
        super(context, pub);
    }

    @Override
    public void loadAd() {
        admobbanner = new AdView(mContext);
        admobbanner.setAdSize(AdSize.SMART_BANNER);
        admobbanner.setAdUnitId(mPub);
        admobbanner.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                if (mAdListener != null) {
                    Utils.printInfo("faild " + mPub);
                    mAdListener.onAdBanFailedToLoad();
                    mAdListener = null;  //有些sdk可能一次请求回调多次失败或成功状态，那么我们应该避免这些问题
                }
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (mAdListener != null) {
                    Utils.printInfo("suceess " + mPub);
                    mAdListener.onAdBanLoaded(MobAdmobBan.this);
                    mAdListener = null;  //有些sdk可能一次请求回调多次失败或成功状态，那么我们应该避免这些问题
                }
            }
        });
        Utils.printInfo("loadAd " + getTag() + mPub);

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
        admobbanner.loadAd(reqBuild.build());

    }

    @Override
    public View getBannerView() {
        return admobbanner;
    }

    @Override
    public void initSdk() {

    }

    @Override
    public String getTag() {
        return "Admob ban";
    }
}
