package com.allever.app.ad;

import android.content.Context;

//import com.allever.lib.ad.admob.MobAdmobBan;
//import com.allever.lib.ad.admob.MobAdmobInter;
import com.allever.lib.ad.facebook.MobFacebookBan;
import com.allever.lib.ad.facebook.MobFacebookInter;
import com.mob.core.MobBannerAd;
import com.mob.core.MobInterstitialAd;
import com.mob.main.IMobAdFactory;

/**
 * @author allever
 */
public class AdFactory implements IMobAdFactory {
    @Override
    public MobBannerAd getBannerAd(Context context, String cate, String pub, String appid) {
        MobBannerAd ad  =null;
        switch (cate) {
            case "F":
                ad = new MobFacebookBan(context,pub);
                break;
            case "A":
//                ad = new MobAdmobBan(context,pub);
                break;
        }
        return ad;
    }

    @Override
    public MobInterstitialAd getInterstitialAd(Context context, String cate, String pub, String appid) {
        MobInterstitialAd ad  =null;
        switch (cate) {
            case "F":
                ad = new MobFacebookInter(context,pub);
                break;
            case "A":
//                ad = new MobAdmobInter(context,pub);
                break;
        }
        return ad;
    }
}
