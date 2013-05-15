package cn.wang.yin.personal.service;

import java.util.Timer;
import java.util.TimerTask;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import cn.wang.yin.utils.CollectGpsUtil;
import cn.wang.yin.utils.PersonConstant;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class PersonService extends IntentService {
	public LocationClient mLocationClient = null;
	public BDLocationListener myListener = new MyLocationListener();

	public PersonService() {
		super("PersonService");
	}

	public PersonService(String name) {
		super("PersonService");
	}

	@Override
	public void onCreate() {
		mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
		mLocationClient.registerLocationListener(myListener); // 注册监听函数
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setAddrType("all");// 返回的定位结果包含地址信息
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan((int) PersonConstant.WAIT_TIMS);// 设置发起定位请求的间隔时间为5000ms
		option.disableCache(true);// 禁止启用缓存定位
		// option.setPoiNumber(5); // 最多返回POI个数
		// option.setPoiDistance(1000); // poi查询距离
		// option.setPoiExtraInfo(true); // 是否需要POI的电话和地址等详细信息
		mLocationClient.setLocOption(option);
		mLocationClient.start();
		super.onCreate();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {

		// TODO Auto-generated method stub
		// location();
		final Timer timer = new Timer();
		final TimerTask task;
		final Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				CollectGpsUtil.uploadGps();
				super.handleMessage(msg);
			}
		};
		task = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message message = new Message();
				message.what = 1;
				handler.sendMessage(message);
			}
		};
		timer.schedule(task, PersonConstant.WAIT_TIMS * 5,
				PersonConstant.WAIT_TIMS * 5);
		super.onStart(intent, startId);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// MainActivity.
	}

	// public void location() {
	//
	// Toast.makeText(getApplicationContext(), "定位数据获取成功,正在保存数据",
	// Toast.LENGTH_SHORT);
	// if (mLocationClient.isStarted())
	// mLocationClient.requestLocation();
	// }
	//
	public class MyLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {

			if (location == null)
				return;
			CollectGpsUtil.saveGps(location);

		}

		@Override
		public void onReceivePoi(BDLocation poiLocation) {
			if (poiLocation == null) {
				return;
			}
		}
	}
}
