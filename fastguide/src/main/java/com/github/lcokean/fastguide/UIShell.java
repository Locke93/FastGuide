package com.github.lcokean.fastguide;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

/**
 * Created by pengjian on 2015/10/30 0030.
 */
public class UIShell extends Activity {
    private static final String TAG = UIShell.class.getSimpleName();
    private static HashMap<String, FakeActivity> executors = new HashMap();
    private FakeActivity executor;

    public static String registerExecutor(FakeActivity paramFakeActivity) {
        String str = String.valueOf(System.currentTimeMillis());
        return registerExecutor(str, paramFakeActivity);
    }

    public static String registerExecutor(String paramString, FakeActivity paramFakeActivity) {
        executors.put(paramString, paramFakeActivity);
        return paramString;
    }

    @Override
    protected void onCreate(Bundle paramBundle) {
        Intent localIntent = getIntent();
        String str1 = localIntent.getStringExtra("launch_time");
        this.executor = ((FakeActivity) executors.remove(str1));
        if (this.executor == null) {
            String str2 = localIntent.getScheme();
            this.executor = ((FakeActivity) executors.remove(str2));
            if (this.executor == null) {
                throw new RuntimeException("Executor lost! launchTime = " + str1);
                //super.onCreate(paramBundle);
                //finish();
                //return;
            }
        }
        Log.d(TAG, "UIShell found executor: " + this.executor.getClass());
        this.executor.setActivity(this);
        super.onCreate(paramBundle);
        this.executor.onCreate();
    }

    public void setContentView(int paramInt) {
        View localView = LayoutInflater.from(this).inflate(paramInt, null);
        setContentView(localView);
    }

    public void setContentView(View paramView) {
        super.setContentView(paramView);
        if (this.executor != null) {
            this.executor.setContentView(paramView);
        }
    }

    public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams) {
        super.setContentView(paramView, paramLayoutParams);
        if (this.executor != null) {
            this.executor.setContentView(paramView);
        }
    }

    protected void onNewIntent(Intent paramIntent) {
        if (this.executor == null) {
            super.onNewIntent(paramIntent);
        } else {
            this.executor.onNewIntent(paramIntent);
        }
    }

    protected void onStart() {
        if (this.executor != null) {
            this.executor.onStart();
        }
        super.onStart();
    }

    protected void onResume() {
        if (this.executor != null) {
            this.executor.onResume();
        }
        super.onResume();
    }

    protected void onPause() {
        if (this.executor != null) {
            this.executor.onPause();
        }
        super.onPause();
    }

    protected void onStop() {
        if (this.executor != null) {
            this.executor.onStop();
        }
        super.onStop();
    }

    protected void onRestart() {
        if (this.executor != null) {
            this.executor.onRestart();
        }
        super.onRestart();
    }

    protected void onDestroy() {
        if (this.executor != null) {
            this.executor.sendResult();
            this.executor.onDestroy();
        }
        super.onDestroy();
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        if (this.executor != null) {
            this.executor.onActivityResult(paramInt1, paramInt2, paramIntent);
        }
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }

    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
        boolean bool = false;
        if (this.executor != null) {
            bool = this.executor.onKeyEvent(paramInt, paramKeyEvent);
        }
        return bool ? true : super.onKeyDown(paramInt, paramKeyEvent);
    }

    public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent) {
        boolean bool = false;
        if (this.executor != null) {
            bool = this.executor.onKeyEvent(paramInt, paramKeyEvent);
        }
        return bool ? true : super.onKeyUp(paramInt, paramKeyEvent);
    }

    public void onConfigurationChanged(Configuration paramConfiguration) {
        super.onConfigurationChanged(paramConfiguration);
        if (this.executor != null) {
            this.executor.onConfigurationChanged(paramConfiguration);
        }
    }

    public void finish() {
        if ((this.executor != null) && (this.executor.onFinish())) {
            return;
        }
        super.finish();
    }
}
