package cn.wang.yin.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.wang.yin.personal.R;

public class GuideViewActivity extends Activity {
	private ImageView mImageView;
	View mView;
	private ArrayList<View> mPageViews;
	// 定义LayoutInflater
	LayoutInflater mInflater;
	private ViewPager mViewPager;
	LinearLayout mLinearLayout;
	private ImageView[] mImageViews;
	ImageButton imgbtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置窗口无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mInflater = getLayoutInflater();
		mPageViews = new ArrayList<View>();
		mPageViews.add(mInflater.inflate(R.layout.boot_1, null));
		mPageViews.add(mInflater.inflate(R.layout.boot_2, null));
		mPageViews.add(mInflater.inflate(R.layout.boot_3, null));
		View v = mInflater.inflate(R.layout.boot_4, null);
		mPageViews.add(v);
		imgbtn = (ImageButton) v.findViewById(R.id.first_open_imageButton1);
		mImageViews = new ImageView[mPageViews.size()];
		mView = mInflater.inflate(R.layout.boot, null);
		mViewPager = (ViewPager) mView.findViewById(R.id.myviewpager);
		mLinearLayout = (LinearLayout) mView
				.findViewById(R.id.mybottomviewgroup);
		for (int i = 0; i < mImageViews.length; i++) {
			mImageView = new ImageView(GuideViewActivity.this);
			mImageView.setLayoutParams(new LayoutParams(20, 20));
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			lp.setMargins(20, 0, 20, 20);
			mImageView.setLayoutParams(lp);

			if (i == 0) {
				mImageView
						.setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				mImageView
						.setBackgroundResource(R.drawable.page_indicator_unfocused);
			}

			mImageViews[i] = mImageView;

			// 把指示作用的远点图片加入底部的视图中
			mLinearLayout.addView(mImageViews[i]);
		}
		setContentView(mView);
		mViewPager.setAdapter(new MyPagerAdapter());
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				for (int i = 0; i < mImageViews.length; i++) {
					if (i == arg0) {
						mImageViews[i]
								.setBackgroundResource(R.drawable.page_indicator_focused);
					} else {
						mImageViews[i]
								.setBackgroundResource(R.drawable.page_indicator_unfocused);
					}
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		imgbtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	@Override
	protected void onDestroy() {
		//EtongDbUtils.putValue(EtongConstant.USER_FIRST_OPEN, true, null);
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mPageViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return super.getItemPosition(object);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			// TODO Auto-generated method stub
			((ViewPager) arg0).removeView(mPageViews.get(arg1));
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			// TODO Auto-generated method stub
			((ViewPager) arg0).addView(mPageViews.get(arg1));
			return mPageViews.get(arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void finishUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

	}
}
