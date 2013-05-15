package cn.wang.yin.personal.service.listenner;

import cn.wang.yin.utils.CollectGpsUtil;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

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