package cn.wang.yin.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.wang.yin.personal.R;
import cn.wang.yin.personal.service.HandlerService;
import cn.wang.yin.utils.PersonConstant;
import cn.wang.yin.utils.PersonIntence;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.GraphicsOverlay;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.map.PopupOverlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class FragmentLocation extends Fragment {

	View mPopView = null;
	TextView pop_text;
	BMapManager mBMapMan = null;
	public static MapView mMapView = null;
	GraphicsOverlay graphicsOverlay = null;
	// List<LocationInfo> listLocation = new ArrayList();
	MapController mMapController;
	Timer timer = new Timer();
	TimerTask task;
	protected static TabChangeReceiver receiver;
	PopupOverlay pop = null;
	MyLocationOverlay myLocationOverlay = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		// PersonDbUtils.init(
		// getApplicationContext(),
		// getSharedPreferences(PersonConstant.USER_AGENT_INFO,
		// Context.MODE_PRIVATE));
		super.onCreate(savedInstanceState);

		// push("开始启动服务");
		// savedInstanceState.startService(new Intent(getApplicationContext(),
		// HandlerService.class));
		// Field[] fields = Build.class.getDeclaredFields();
		// System.out.println(15 / 0);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		LayoutInflater myInflater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = myInflater.inflate(R.layout.location, container, false);
		mBMapMan = new BMapManager(getActivity().getApplicationContext());
		mBMapMan.init(PersonConstant.BAIDU_MAP_KEY, null);
		// layout.setContentView(R.layout.location);
		mMapView = (MapView) layout.findViewById(R.id.sinagle_taavel_map_view);
		mMapView.setBuiltInZoomControls(false);
		// 设置启用内置的缩放控件
		mMapController = mMapView.getController();
		mMapController.setCenter(PersonIntence.getPoint());// 设置地图中心点
		mMapController.setZoom(19);// 设置地图zoom级别
		graphicsOverlay = new GraphicsOverlay(mMapView);
		mMapView.getOverlays().add(graphicsOverlay);

		myLocationOverlay = new MyLocationOverlay(mMapView);

		myLocationOverlay.setData(PersonIntence.getLocData());
		mMapView.getOverlays().add(myLocationOverlay);
		myLocationOverlay.enableCompass();
		mMapView.refresh();// 刷新地图
		if (!HandlerService.isRunning()) {
			getActivity().startService(
					new Intent(getActivity().getApplicationContext(),
							HandlerService.class));
		}

		return layout;

	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				break;
			}

		}
	};

	public class TabChangeReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Log.i("onReceive", "接收到");
			int intExtra = intent.getIntExtra(
					PersonConstant.LOCATION_CHANGE_TAG, 0);
			if (PersonConstant.LOCATION_CHANGE == intExtra) {
				myLocationOverlay.setData(PersonIntence.getLocData());
				mMapView.refresh();
			}
		}
	}

	// ItemizedOverlay
	public class OverItemS extends ItemizedOverlay<OverlayItem> {

		private List<OverlayItem> GeoList = new ArrayList<OverlayItem>();

		public OverItemS(Drawable drawable, MapView mapView) {
			super(drawable, mapView);
			// TODO Auto-generated constructor stub
		}

		@Override
		// 处理当点击事件
		protected boolean onTap(int i) {
			OverlayItem overItem = getItem(i);
			GeoPoint pt = overItem.getPoint();
			mPopView.setVisibility(View.VISIBLE);
			// pop.showPopup(null, pt, BIND_ABOVE_CLIENT);
			// mPopView.setX(pt.getLatitudeE6());
			// mPopView.setY(pt.getLongitudeE6());
			pop_text.setText(overItem.getSnippet());
			return true;
		}

		@Override
		public boolean onTap(GeoPoint arg0, MapView arg1) {
			// 消去弹出的气泡
			mPopView.setVisibility(View.GONE);
			return super.onTap(arg0, arg1);
		}
	}

	@Override
	public void onResume() {
		receiver = new TabChangeReceiver();
		getActivity().registerReceiver(receiver,
				new IntentFilter("cn.wang.yin.ui.FragmentLocation"), null,
				handler);
		super.onResume();
	}

	@Override
	public void unregisterForContextMenu(View view) {
		// TODO Auto-generated method stub
		super.unregisterForContextMenu(view);
	}

	public static void launch(Context c) {
		Intent intent = new Intent(c, Location.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		c.startActivity(intent);
	}

}
