package com.github.lcokean.fastguide.listener;

import android.view.View;

import java.io.Serializable;

/**
 * Created by pengjian on 2015/10/30 0030.
 */
public interface ViewLazyInitializeListener extends Serializable {
    void onViewLazyInitialize(View view, int position);
}
