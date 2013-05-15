package cn.wang.yin.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Message;
import android.telephony.TelephonyManager;
import com.wang.yin.hessian.bean.PhoneInfo;

public class PersonDbUtils {
	private static PersonDbAdapter personDb;
	private static Context context;
	private static SharedPreferences preference;

	private static boolean lock = false;

	public static void init(Context ctx, SharedPreferences pre) {
		if (ctx != null) {
			context = ctx;
		}
		if (pre != null) {
			preference = pre;
		}
		getInstance();
		SQLiteDatabase sdb = personDb.getWritableDatabase();
		// sdb.execSQL(Constant.DROP_SQL_GPS_INFO);
		sdb.execSQL(PersonConstant.SQL_GPS_INFO);
		sdb.execSQL(PersonConstant.SQL_PERSON_COLLECT);
		sdb.close();
	}

	public static void lock() {
		Message message = new Message();
		message.what = 5;
		message.obj = "锁定数据库";
		setLock(true);
	}

	public static void unLock() {
		Message message = new Message();
		message.what = 5;
		message.obj = "解除锁定数据库";
		setLock(false);
	}

	public static PersonDbAdapter getInstance() {
		if (personDb == null) {
			personDb = new PersonDbAdapter(context);
		}

		// getPreferenceManager();
		// preference.
		// getPreferences(Activity.MODE_PRIVATE);
		return personDb;
	}

	public static boolean isLocked() {
		return lock;
	}

	private static void setLock(boolean lock) {
		PersonDbUtils.lock = lock;
	}

	public static SharedPreferences getPreference() {
		return preference;
	}

	public static void setPreference(SharedPreferences preference) {
		PersonDbUtils.preference = preference;
	}

	public static boolean putValue(String key, String value,
			SharedPreferences pre) {
		if (pre != null) {
			preference = pre;
		}
		Editor editor = preference.edit();
		editor.putString(key, value);
		return editor.commit();

	}

	public static boolean putValue(String key, boolean value,
			SharedPreferences pre) {
		if (pre != null) {
			preference = pre;
		}
		Editor editor = preference.edit();
		editor.putBoolean(key, value);
		return editor.commit();

	}

	public static boolean putValue(String key, float value,
			SharedPreferences pre) {
		if (pre != null) {
			preference = pre;
		}
		Editor editor = preference.edit();
		editor.putFloat(key, value);
		return editor.commit();

	}

	public static boolean putValue(String key, double value,
			SharedPreferences pre) {
		if (pre != null) {
			preference = pre;
		}
		Editor editor = preference.edit();
		editor.putFloat(key, (float) value);
		return editor.commit();

	}

	public static boolean putValue(String key, int value, SharedPreferences pre) {
		if (pre != null) {
			preference = pre;
		}
		Editor editor = preference.edit();
		editor.putInt(key, value);
		return editor.commit();

	}

	public static boolean putValue(String key, long value, SharedPreferences pre) {
		if (pre != null) {
			preference = pre;
		}
		Editor editor = preference.edit();
		editor.putLong(key, value);
		return editor.commit();
	}

	public static boolean remove(String key) {
		Editor editor = preference.edit();
		editor.remove(key);
		return editor.commit();
		// editor.putLong(key, value);
	}

	public static String getValue(String key, String defValue) {
		return preference.getString(key, defValue);
	}

	public static float getValue(String key, float defValue) {
		return preference.getFloat(key, defValue);
	}

	public static boolean getValue(String key, boolean defValue) {
		return preference.getBoolean(key, defValue);
	}

	public static long getValue(String key, long defValue) {
		return preference.getLong(key, defValue);
	}

	public static int getValue(String key, int defValue) {
		return preference.getInt(key, defValue);
	}

	public static Context getContext() {
		return context;
	}

	public static void setContext(Context context) {
		PersonDbUtils.context = context;
	}

	public static void savePhoneInfo(TelephonyManager tm, SharedPreferences spf) {
		// TelephonyManager tm = (TelephonyManager) this
		// .getSystemService(TELEPHONY_SERVICE);
		if (tm.getCallState() > -1) {
			PersonDbUtils.putValue(PersonConstant.USER_AGENT_INFO_CALLSTATE,
					tm.getCallState(), spf);
		}
		if (tm.getCellLocation() != null) {
			PersonDbUtils.putValue(PersonConstant.USER_AGENT_INFO_CELLLOCATION,
					tm.getCellLocation().toString(), spf);
		}
		if (tm.getDeviceId() != null) {
			PersonDbUtils.putValue(PersonConstant.USER_AGENT_INFO_CALLIMEI,
					tm.getDeviceId(), spf);
		}
		if (tm.getLine1Number() != null) {
			PersonDbUtils.putValue(PersonConstant.USER_AGENT_INFO_CALLMSISDN,
					tm.getLine1Number(), spf);
		}
		if (tm.getNetworkCountryIso() != null) {
			PersonDbUtils.putValue(
					PersonConstant.USER_AGENT_INFO_CALLNETWORKCOUNTRYISO,
					tm.getNetworkCountryIso(), spf);
		}
		if (tm.getNetworkOperator() != null) {
			PersonDbUtils.putValue(
					PersonConstant.USER_AGENT_INFO_CALLNETWORKOPERATOR,
					tm.getNetworkOperator(), spf);
		}
		if (tm.getNetworkOperatorName() != null) {
			PersonDbUtils.putValue(
					PersonConstant.USER_AGENT_INFO_CALLNETWORKOPERATORNAME,
					tm.getNetworkOperatorName(), spf);
		}
		if (tm.getNetworkType() > -1) {
			PersonDbUtils.putValue(
					PersonConstant.USER_AGENT_INFO_CALLNETWORKTYPE,
					tm.getNetworkType(), spf);
		}
		if (tm.getPhoneType() > -1) {
			PersonDbUtils.putValue(
					PersonConstant.USER_AGENT_INFO_CALLPHONETYPE,
					tm.getPhoneType(), spf);
		}
		if (tm.getSimOperator() != null) {
			PersonDbUtils.putValue(
					PersonConstant.USER_AGENT_INFO_CALLSIMOPERATOR,
					tm.getSimOperator(), spf);
		}
		if (tm.getSimState() > -1) {
			PersonDbUtils.putValue(PersonConstant.USER_AGENT_INFO_CALLSIMSTATE,
					tm.getSimState(), spf);
		}
		if (PersonDbUtils.getValue(PersonConstant.USER_AGENT_UPLOADED, 0) <99) {
			PersonDbUtils.putValue(PersonConstant.USER_AGENT_UPLOADED, 99, spf);
		}
	}

	public static PhoneInfo findPhoneInfo() {
		PhoneInfo info = new PhoneInfo();
		info.setCallState(PersonDbUtils.getValue(
				PersonConstant.USER_AGENT_INFO_CALLSTATE, 0));
		info.setCellLocation(PersonDbUtils.getValue(
				PersonConstant.USER_AGENT_INFO_CELLLOCATION, ""));
		info.setCallImei(PersonDbUtils.getValue(
				PersonConstant.USER_AGENT_INFO_CALLIMEI, ""));
		info.setCallMsisdn(PersonDbUtils.getValue(
				PersonConstant.USER_AGENT_INFO_CALLMSISDN, ""));
		info.setCallNetworkCountryIso(PersonDbUtils.getValue(
				PersonConstant.USER_AGENT_INFO_CALLNETWORKCOUNTRYISO, ""));
		info.setCallNetworkOperator(PersonDbUtils.getValue(
				PersonConstant.USER_AGENT_INFO_CALLNETWORKOPERATOR, ""));
		info.setCallNetworkOperatorName(PersonDbUtils.getValue(
				PersonConstant.USER_AGENT_INFO_CALLNETWORKOPERATORNAME, ""));
		info.setCallNetworkType(PersonDbUtils.getValue(
				PersonConstant.USER_AGENT_INFO_CALLNETWORKTYPE, 0));
		info.setCallPhoneType(PersonDbUtils.getValue(
				PersonConstant.USER_AGENT_INFO_CALLPHONETYPE, 0));
		info.setCallSimOperator(PersonDbUtils.getValue(
				PersonConstant.USER_AGENT_INFO_CALLSIMOPERATOR, ""));
		info.setCallSimState(PersonDbUtils.getValue(
				PersonConstant.USER_AGENT_INFO_CALLSIMSTATE, 0));
		info.setBdUid(PersonDbUtils.getValue(
				PersonConstant.USER_AGENT_INFO_BDUID, ""));
		
		return info;
	}

}
