package cn.wang.yin.utils;

import org.apache.commons.lang.StringUtils;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;
import cn.wang.yin.ui.CrashHandler;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MKEvent;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class PersonIntence extends Application {
	private static GeoPoint point;
	private static String addr;
	private static PersonIntence instance = null;
	BMapManager mBMapManager = null;
	public boolean bd_KeyRight = true;
	public static final String strKey = "76704AFCB361E05D0738DD3B3542D0A88236ECC1";
	private static LocationData locData;

	@Override
	public void onCreate() {
		super.onCreate();
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(getApplicationContext());
	}

	public PersonIntence() {

	}

	public static LocationData getLocData() {
		if (locData == null) {
			locData = new LocationData();
			locData.latitude = 22.594101;
			locData.longitude = 113.971166;
			locData.accuracy = 50;
			locData.direction = 1;
		}
		return locData;
	}

	public static void setLocData(LocationData locData) {
		PersonIntence.locData = locData;
	}

	@Override
	// 建议在您app的退出之前调用mapadpi的destroy()函数，避免重复初始化带来的时间消耗
	public void onTerminate() {
		// TODO Auto-generated method stub
		if (mBMapManager != null) {
			mBMapManager.destroy();
			mBMapManager = null;
		}
		super.onTerminate();
	}

	public void initEngineManager(Context context) {
		if (mBMapManager == null) {
			mBMapManager = new BMapManager(context);
		}

		if (!mBMapManager.init(strKey, new MyGeneralListener())) {
			Toast.makeText(getApplicationContext(), "BMapManager  初始化错误!",
					Toast.LENGTH_LONG).show();
		}
	}

	public static PersonIntence getInstance() {
		return instance;
	}

	public static void setInstance(PersonIntence instance) {
		PersonIntence.instance = instance;
	}

	public BMapManager getmBMapManager() {
		return mBMapManager;
	}

	public void setmBMapManager(BMapManager mBMapManager) {
		this.mBMapManager = mBMapManager;
	}

	public static void setLon(double lon) {
		if (point == null) {
			point = new GeoPoint((int) (22.594101 * 1E6),
					(int) (113.971166 * 1e6));
		}
		point.setLongitudeE6((int) (lon * 1e6));
	}

	public static void setLat(double lat) {
		if (point == null) {
			point = new GeoPoint((int) (22.594101 * 1E6),
					(int) (113.971166 * 1e6));
		}
		point.setLatitudeE6((int) (lat * 1e6));
	}

	public static GeoPoint getPoint() {
		if (point == null) {
			point = new GeoPoint((int) (22.594101 * 1E6),
					(int) (113.971166 * 1e6));
		}
		return point;
	}

	public static String getAddr() {
		addr = StringUtils.isNotBlank(addr) ? addr : "位置未知";
		return addr;
	}

	public static void setAddr(String addr) {
		PersonIntence.addr = addr;
	}

	static class MyGeneralListener implements MKGeneralListener {

		@Override
		public void onGetNetworkState(int iError) {
			if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
				Toast.makeText(
						PersonIntence.getInstance().getApplicationContext(),
						"您的网络出错啦！", Toast.LENGTH_LONG).show();
			} else if (iError == MKEvent.ERROR_NETWORK_DATA) {
				Toast.makeText(
						PersonIntence.getInstance().getApplicationContext(),
						"输入正确的检索条件！", Toast.LENGTH_LONG).show();
			}
			// ...
		}

		@Override
		public void onGetPermissionState(int iError) {
			if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
				// 授权Key错误：
				Toast.makeText(
						PersonIntence.getInstance().getApplicationContext(),
						"请在 DemoApplication.java文件输入正确的授权Key！",
						Toast.LENGTH_LONG).show();
				PersonIntence.getInstance().bd_KeyRight = false;
			}
		}
	}
}
