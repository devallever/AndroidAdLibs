package com.mob.core;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.mob.bean.MobAdbean;
import com.mob.main.IMobAdListener;
import com.mob.bean.Model;
import com.mob.tool.Utils;

import org.json.JSONObject;


/**
 * @author allever
 */
public class MobAdInter extends MobAd implements IMobAdInterListener {
    public MobAdInter(Context context) {
        super(context);
    }

    @Override
    public void popAdShow() {
        try {
            if (mPolicyAds.isEmpty()) {
                Utils.printInfo("inter ads empty");
                if (mMobListener != null) {
                    mMobListener.onAdFailedToLoad();
                }
                return;
            }
            MobAdbean adbean = mPolicyAds.removeFirst();
            MobInterstitialAd interstitialAd = null;
            if (adbean != null) {
                if (Utils.gFactory != null) {
                    interstitialAd = Utils.gFactory.getInterstitialAd(mContext, adbean.getCategory(), adbean.getPub(), adbean.getAppId());
                    if (interstitialAd != null) {
                        interstitialAd.setAdListener(this);
                        interstitialAd.loadAd();
                    }
                } else {
                    Utils.printInfo("MobAdInter Factory is null");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utils.printInfo(e.toString());
        }
    }

    private IMobAdListener mMobListener = null;

    @Override
    public void loadAd(final Activity activity, final String tag, final IMobAdListener iMobAdListener) {
        mMobListener = iMobAdListener;
        try {
            String data = Model.getInstance().getOnlineData(tag);
            if (TextUtils.isEmpty(data)) { //如果参数为空，则在等待数据
                Model.getInstance().registDataChangeObserver(new Model.NotifyObserver() {
                    @Override
                    public void onDataChange(JSONObject jsonObject) {
                        Utils.gHandle.post(new Runnable() {
                            @Override
                            public void run() {
                                Utils.printInfo("ondata " + tag);
                                loadAd(activity, tag, iMobAdListener);
                            }
                        });
                    }
                });
                return;
            }
            //解析数据
            //
            paraseData(mPolicyAds, data);
            //解析完数据后从广告链表中提取广告。
            popAdShow();
        } catch (Exception e) {
            e.printStackTrace();
            ;
            Utils.printInfo(e.toString());
            if (mMobListener != null) {
                mMobListener.onAdFailedToLoad();
            }
        }
    }


    private MobInterstitialAd mMobInter = null;

    @Override
    public void onAdLoaded(MobInterstitialAd interstitialad) {
        mMobInter = interstitialad;
        if (mMobListener != null) {
            mMobListener.onAdLoaded(this);
        }
    }

    @Override
    public void showAd() {
        if (mMobInter != null) {
            mMobInter.showAd();
            mMobInter = null;
        }
    }

    @Override
    public void showAd(Activity activity) {
        if (mMobInter != null) {
            mMobInter.showAd(activity);
            mMobInter = null;
        }
    }

    @Override
    public boolean isLoaded() {
        return mMobInter != null ? true : false;
    }

    @Override
    public void onAdFailedToLoad() {
        popAdShow();
    }

    @Override
    public int getAdType() {
        return INTERSTITIAL;
    }
}
