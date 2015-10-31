package com.github.lcokean.fastguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;

import java.util.HashMap;

/**
 * Created by pengjian on 2015/10/30 0030.
 */
public class FakeActivity {
    private static Class<? extends UIShell> shell;
    protected Activity activity;
    private FakeActivity resultReceiver;
    private HashMap<String, Object> result;
    private View contentView;

    public static void setShell(Class<? extends UIShell> paramClass) {
        shell = paramClass;
    }

    public void setActivity(Activity paramActivity) {
        this.activity = paramActivity;
    }

    final void setContentView(View paramView) {
        this.contentView = paramView;
    }

    public View getContentView() {
        return this.contentView;
    }

    public void onCreate() {
    }

    protected void onNewIntent(Intent paramIntent) {
    }

    public void onStart() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onStop() {
    }

    public void onRestart() {
    }

    public boolean onFinish() {
        return false;
    }

    public void onDestroy() {
    }

    public final void finish() {
        this.activity.finish();
    }

    public boolean onKeyEvent(int paramInt, KeyEvent paramKeyEvent) {
        return false;
    }

    public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    }

    public void startActivity(Intent paramIntent) {
        startActivityForResult(paramIntent, -1);
    }

    public void startActivityForResult(Intent paramIntent, int paramInt) {
        this.activity.startActivityForResult(paramIntent, paramInt);
    }

    public void onConfigurationChanged(Configuration paramConfiguration) {
    }

    public Context getContext() {
        return this.activity;
    }

    public void show(Context paramContext, Intent paramIntent) {
        showForResult(paramContext, paramIntent, null);
    }

    public void showForResult(Context paramContext, Intent paramIntent, FakeActivity paramFakeActivity) {
        this.resultReceiver = paramFakeActivity;
        Message localMessage = new Message();
        Intent localIntent = new Intent(paramContext, shell == null ? UIShell.class : shell);
        String str = UIShell.registerExecutor(this);
        localIntent.putExtra("launch_time", str);
        if (paramIntent != null) {
            localIntent.putExtras(paramIntent);
        }
        if (!(paramContext instanceof Activity)) {
            localIntent.addFlags(268435456);
        }
        localIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        paramContext.startActivity(localIntent);
    }

    public FakeActivity getParent() {
        return this.resultReceiver;
    }

    public final void setResult(HashMap<String, Object> paramHashMap) {
        this.result = paramHashMap;
    }

    void sendResult() {
        if (this.resultReceiver != null) {
            this.resultReceiver.onResult(this.result);
        }
    }

    public void onResult(HashMap<String, Object> paramHashMap) {
    }

}
