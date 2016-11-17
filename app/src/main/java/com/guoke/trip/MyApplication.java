package com.guoke.trip;

import com.guoke.trip.activity.MainActivity;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;

/**
 * Created by th on 2016/11/17.
 * 类说明:
 */

public class MyApplication extends Application implements Thread.UncaughtExceptionHandler {
    private static final String BUGLY_APP_ID = "900039473";


    private static MyApplication mInstance = null;
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    public MyApplication() {
        mInstance = this;
    }

    public static MyApplication getInstances() {
        if (null == mInstance) {
            mInstance = new MyApplication();
        }
        return mInstance;
    }

    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);

//        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Constants.DOWNLOAD_APK);
//        Beta.upgradeDialogLayoutId = R.layout.bugly_update_dialog;
//        Beta.canShowUpgradeActs.add(MainActivity.class);
//        Bugly.init(getApplicationContext(), MyApplication.BUGLY_APP_ID, false);
//
//        if (Log166.isDebug()) {
//            OkHttpUtils.getInstance().debug(Key.NIL);
//            // Looper.getMainLooper().setMessageLogging(new LogPrinter(getApplicationContext()));
//        }
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        ex.printStackTrace();
        if (mDefaultHandler != null) {
            Intent go2Main = new Intent(getApplicationContext(), MainActivity.class);
            PendingIntent intent = PendingIntent.getActivity(getBaseContext(), 0, go2Main, go2Main.getFlags());
            ((AlarmManager) getSystemService(Context.ALARM_SERVICE)).set(AlarmManager.RTC, System.currentTimeMillis() + 500, intent);
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(2);
        }
    }

}

