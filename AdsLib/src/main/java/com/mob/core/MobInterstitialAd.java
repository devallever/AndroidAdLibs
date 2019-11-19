package com.mob.core;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/25.
 */
public abstract class MobInterstitialAd {

    public abstract void showAd();

    public void showAd(Activity activity) {
        showAd();
    }

    private List<String> mTestDeviceList = new ArrayList<>();

    protected IMobAdInterListener mAdListener = null;

    public void setAdListener(IMobAdInterListener adListener) {
        mAdListener = adListener;
    }

    protected Context mContext = null;
    protected String mPub = null;

    public MobInterstitialAd(Context context, String pub) {
        mContext = context;
        mPub = pub;
        initSdk();
        initTestDevice();
    }

    public MobInterstitialAd(Context context) {
        mContext = context;
        initSdk();
    }

    public void setPub(String pub) {
        mPub = pub;
    }

    public abstract void loadAd();

    public abstract void initSdk();

    public abstract String getTag();

    private void initTestDevice() {
        if (mTestDeviceList.size() > 0) {
            mTestDeviceList.clear();
        }
        mTestDeviceList.add("1621DB3C172AE6711BA840F4AEF6EF48");//Nexus5
    }

    protected List<String> getTestDeviceList() {
        return mTestDeviceList;
    }
}
