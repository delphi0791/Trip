package com.guoke.trip.activity;

import com.guoke.trip.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by th on 2016/11/17.
 * 类说明:
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }
}
