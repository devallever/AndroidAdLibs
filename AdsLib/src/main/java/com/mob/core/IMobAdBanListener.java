package com.mob.core;

/**
 * @author allever
 */
public interface IMobAdBanListener {
    void onAdBanLoaded(MobBannerAd mobban);

    void onAdBanFailedToLoad();
}
