package cn.wang.yin.personal.service.listenner;

import android.content.Context;

import cn.wang.yin.utils.CollectGpsUtil;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class LocationProvider {
	private static LocationClient mLocationClient = null;

	private static MyBDListener listener = new MyBDListener();

	Context context;

	public LocationProvider(Context context) {
		super();
		this.context = context;
	}

	public void startLocation() {
		mLocationClient = new LocationClient(context);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型为bd09ll
		option.setPriority(LocationClientOption.NetWorkFirst); // 设置网络优先
		option.setProdName("demo"); // 设置产品线名称
		mLocationClient.setLocOption(option);
		mLocationClient.registerLocationListener(listener);
		mLocationClient.start();// 将开启与获取位置分开，就可以尽量的在后面的使用中获取到位置
	}

	/**
	 * 停止，减少资源消耗
	 */
	public void stopListener() {
		if (mLocationClient != null && mLocationClient.isStarted()) {
			mLocationClient.stop();
			mLocationClient = null;
		}
	}

	/**
	 * 更新位置并保存到SItude中
	 */
	public void updateListener() {
		if (mLocationClient != null && mLocationClient.isStarted()) {
			mLocationClient.requestLocation();
		}
	}

	private static class MyBDListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location.getCity() == null) {
				int type = mLocationClient.requestLocation();
				return;
			}
			CollectGpsUtil.saveGps(location);
		}

		@Override
		public void onReceivePoi(BDLocation arg0) {
			// return
		}

	}
}