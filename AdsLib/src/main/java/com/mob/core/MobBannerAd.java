package com.mob.core;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */
public abstract class MobBannerAd {

    protected IMobAdBanListener mAdListener = null;
    private List<String> mTestDeviceList = new ArrayList<>();

    public void setAdListener(IMobAdBanListener adListener) {
        mAdListener = adListener;
    }

    protected Context mContext = null;
    public String mPub = null;

    public MobBannerAd(Context context, String pub) {
        mContext = context;
        mPub = pub;
        initSdk();
        initTestDevice();
    }

    public MobBannerAd(Context context) {
        mContext = context;
        initSdk();
    }

    public void setPub(String pub) {
        mPub = pub;
    }

    public abstract void loadAd();

    public abstract View getBannerView();

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
