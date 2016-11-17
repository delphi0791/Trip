package com.guoke.trip.activity;

import java.util.Random;

import com.guoke.trip.R;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;

import butterknife.ButterKnife;

/**
 * Created by th on 2016/11/17. 类说明:
 */
public class SplashActivity extends Activity {

	private static final int ANIMATION_DURATION = 2000;
	private static final float SCALE_END = 1.13F;

	private static final int[] SPLASH_ARRAY = { R.drawable.splash0, R.drawable.splash1, R.drawable.splash2, R.drawable.splash3, R.drawable.splash4,
			R.drawable.splash5, R.drawable.splash6, R.drawable.splash7, R.drawable.splash8, R.drawable.splash9, R.drawable.splash10, R.drawable.splash11,
			R.drawable.splash12, R.drawable.splash13, R.drawable.splash14, R.drawable.splash15, R.drawable.splash16, };

	private ImageView mSplashImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		mSplashImage = ButterKnife.findById(this, R.id.image);

		Random r = new Random(SystemClock.elapsedRealtime());
		mSplashImage.setImageResource(SPLASH_ARRAY[r.nextInt(SPLASH_ARRAY.length)]);
		animateImage();
		ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, 1);
	}

	private void animateImage() {
		ObjectAnimator animatorX = ObjectAnimator.ofFloat(mSplashImage, View.SCALE_X, 1f, SCALE_END);
		ObjectAnimator animatorY = ObjectAnimator.ofFloat(mSplashImage, View.SCALE_Y, 1f, SCALE_END);

		AnimatorSet set = new AnimatorSet();
		set.setDuration(ANIMATION_DURATION).play(animatorX).with(animatorY);
		set.start();

		set.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				MainActivity.start(SplashActivity.this);
				SplashActivity.this.finish();
			}
		});
	}

}
