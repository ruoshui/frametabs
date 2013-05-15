package cn.wang.yin.personal.service;

import java.util.Timer;
import java.util.TimerTask;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import cn.wang.yin.personal.R;
import cn.wang.yin.ui.LocationMainActivity;
import cn.wang.yin.utils.CollectGpsUtil;
import cn.wang.yin.utils.PersonConstant;
import cn.wang.yin.utils.PersonDbUtils;
import cn.wang.yin.utils.PersonIntence;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.LocationData;

public class HandlerService extends IntentService {
	public static LocationClient mLocationClient = null;
	public BDLocationListener myListener = new MyLocationListener();
	public static Timer timer = new Timer();
	public static TimerTask task;
	public static Timer uploadTimer = new Timer();
	public static TimerTask uploadTask;
	public static NotificationManager m_NotificationManager;
	Intent m_Intent;
	PendingIntent m_PendingIntent;
	Notification m_Notification;

	public HandlerService() {

		super("HandlerService");
	}

	public HandlerService(String name) {
		super("HandlerService");
	}

	@Override
	public void onCreate() {
		// addNotificaction();
		mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
		mLocationClient.registerLocationListener(myListener); // 注册监听函数
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setAddrType("all");// 返回的定位结果包含地址信息
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		option.setProdName("wangyin");
		option.setPriority(LocationClientOption.GpsFirst);
		option.setScanSpan((int) PersonConstant.WAIT_TIMS);// 设置发起定位请求的间隔时间为5000ms
		option.disableCache(true);// 禁止启用缓存定位
		mLocationClient.setLocOption(option);
		mLocationClient.start();
		PersonDbUtils.setPreference(getSharedPreferences(
				PersonConstant.USER_AGENT_INFO, Context.MODE_PRIVATE));
		PushManager.startWork(getApplicationContext(),
				PushConstants.LOGIN_TYPE_API_KEY, PersonConstant.API_KEY);
		PushConstants.restartPushService(this);
		TelephonyManager tm = (TelephonyManager) this
				.getSystemService(TELEPHONY_SERVICE);
		PersonDbUtils.savePhoneInfo(
				tm,
				getSharedPreferences(PersonConstant.USER_AGENT_INFO,
						Context.MODE_PRIVATE));

		super.onCreate();
	}

	/**
	 * 添加一个notification
	 */
	private void addNotificaction() {

		m_NotificationManager = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);
		// 创建一个Notification
		Notification m_Notification = new Notification();
		// 设置显示在手机最上边的状态栏的图标
		m_Notification.icon = R.drawable.ic_launcher;
		// 当当前的notification被放到状态栏上的时候，提示内容
		m_Notification.tickerText = PersonConstant.MSEESGE_REMIND_TICKER;
		m_Notification.flags |= Notification.FLAG_ONGOING_EVENT;
		m_Notification.flags |= Notification.FLAG_AUTO_CANCEL;
		// m_Notification.flags |= Notification.FLAG_NO_CLEAR;
		// 添加声音提示
		m_Notification.defaults = Notification.DEFAULT_SOUND;
		m_Notification.audioStreamType = android.media.AudioManager.ADJUST_LOWER;
		Intent intent = new Intent(this, LocationMainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				intent, PendingIntent.FLAG_ONE_SHOT);
		m_Notification.setLatestEventInfo(this, "王隐",
				PersonConstant.MSEESGE_REMIND_CONTENT, pendingIntent);
		m_NotificationManager.notify(PersonConstant.COMMON_NOTIFICATION,
				m_Notification);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		uploadTask = new TimerTask() {
			@Override
			public void run() {
				CollectGpsUtil.uploadGps();
				// addNotificaction();
			}
		};
		uploadTimer.schedule(uploadTask, PersonConstant.UPLOAD_TIMS,
				PersonConstant.UPLOAD_TIMS);
		super.onStart(intent, startId);

	}

	@Override
	public IBinder onBind(Intent intent) {

		return super.onBind(intent);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// PushManager.activityStoped(this);
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Message message = new Message();
			message.what = 5;
			// /定时执行任务
			// ////////////////////////////////////////////////////////////////
			switch (msg.what) {
			case 1:
				// if (mLocationClient != null && mLocationClient.isStarted()) {
				// mLocationClient.requestLocation();
				//
				// message.obj = "定位";
				// } else {
				// message.obj = "有异常\t" + mLocationClient;
				// }
				break;
			case 2:
				if (msg.obj != null) {
					message.obj = msg.obj.toString();
				}

				break;
			case 3:
				CollectGpsUtil.location = (BDLocation) msg.obj;
				handler.post(CollectGpsUtil.saveRunnnable);
				if (CollectGpsUtil.getLat() != CollectGpsUtil.location
						.getLatitude()
						|| CollectGpsUtil.getLon() == CollectGpsUtil.location
								.getLongitude()) {
					Log.e("change", "变化");
					Intent intent = new Intent("cn.wang.yin.ui.Location");
					intent.putExtra(PersonConstant.LOCATION_CHANGE_TAG,
							PersonConstant.LOCATION_CHANGE);
					sendBroadcast(intent);
				}

				break;
			case 4:
				if (msg.obj != null) {
					message.obj = msg.obj.toString();
				}
				break;
			}
		}
	};
	Runnable locationRunnnable = new Runnable() {
		@Override
		public void run() {

		}
	};

	Runnable saveRunnnable = new Runnable() {
		@Override
		public void run() {

		}
	};

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub

	}

	public class MyLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {

			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			
			LocationData locData =new LocationData();
			locData.latitude = location.getLatitude();
	        locData.longitude = location.getLongitude();
	        locData.accuracy = location.getRadius();
	        locData.direction = location.getDerect();
	        PersonIntence.setLocData(locData);
			sb.append("\nradius : ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation) {
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				PersonIntence.setAddr(location.getAddrStr());
			}

			Message message = new Message();
			message.what = 2;
			message.obj = sb;
			handler.sendMessage(message);
			Message msg = new Message();
			msg.what = 3;
			msg.obj = location;
			handler.sendMessage(msg);

		}

		@Override
		public void onReceivePoi(BDLocation poiLocation) {
			if (poiLocation == null) {
				return;
			}
		}
	}

}
