package com.mob.core;


/**
 * @author allever
 */
public interface IMobAdInterListener {
    void onAdLoaded(MobInterstitialAd mobInter);

    void onAdFailedToLoad();
}
