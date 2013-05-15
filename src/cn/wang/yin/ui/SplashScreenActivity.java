package cn.wang.yin.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ImageView;
import cn.wang.yin.personal.R;
import cn.wang.yin.utils.PersonConstant;
import cn.wang.yin.utils.PersonDbUtils;

public class SplashScreenActivity extends Activity {
	private AnimationDrawable splashDrawable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_activity);
		PersonDbUtils.init(
				getApplicationContext(),
				getSharedPreferences(PersonConstant.USER_AGENT_INFO,
						Context.MODE_PRIVATE));
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				ImageView imageView = (ImageView) findViewById(R.id.splash_anim);
				splashDrawable = (AnimationDrawable) imageView.getDrawable();
				splashDrawable.start();
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent mainIntent = new Intent(
								SplashScreenActivity.this, FragmentMain.class);
						SplashScreenActivity.this.startActivity(mainIntent);
						FragmentMain.launch(SplashScreenActivity.this);
						SplashScreenActivity.this.finish();

					}
				}, 2000);// 2000为间隔的时间-毫秒
			}
		}, 50);// 50为间隔的时间-毫秒
	}

}