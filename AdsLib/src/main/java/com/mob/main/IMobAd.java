package com.mob.main;

import android.app.Activity;


/**
 * @author allever
 */
public interface IMobAd {

    int BANNER = 1;
    int INTERSTITIAL = 2;

    void loadAd(Activity activity, String tag, IMobAdListener iMobAdListener);

    void showAd();

    void showAd(Activity activity);

    int getAdType();

    boolean isLoaded();
}
