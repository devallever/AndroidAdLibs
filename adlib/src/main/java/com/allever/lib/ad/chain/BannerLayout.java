package com.allever.lib.ad.chain;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.allever.lib.ad.R;

/**
 * @author allever
 */
public class BannerLayout extends RelativeLayout {

    private ViewGroup mBannerContainer;

    public BannerLayout(@NonNull Context context) {
        this(context, null);
    }

    public BannerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.banner_layout, this);
        mBannerContainer = root.findViewById(R.id.adLibBannerContainer);
    }

    public ViewGroup getBannerContainer() {
        return mBannerContainer;
    }
}
