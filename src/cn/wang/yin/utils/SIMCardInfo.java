package cn.wang.yin.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * class name：SIMCardInfo<BR>
 * class description：读取Sim卡信息<BR>
 * PS： 必须在加入各种权限 <BR>
 * Date:2012-3-12<BR>
 * 
 * @version 1.00
 * @author CODYY)peijiangping
 */
public class SIMCardInfo {
	/**
	 * TelephonyManager提供设备上获取通讯服务信息的入口。 应用程序可以使用这个类方法确定的电信服务商和国家 以及某些类型的用户访问信息。
	 * 应用程序也可以注册一个监听器到电话收状态的变化。不需要直接实例化这个类
	 * 使用Context.getSystemService(Context.TELEPHONY_SERVICE)来获取这个类的实例。
	 */
	public static Context cxt;

	private static TelephonyManager telephonyManager;
	/**
	 * 国际移动用户识别码
	 */
	private String IMSI;

	public SIMCardInfo() {
		telephonyManager = (TelephonyManager) cxt
				.getSystemService(Context.TELEPHONY_SERVICE);

	}

	public SIMCardInfo(Context context) {
		context = cxt;
		telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
	}

	public static void init(Context context) {
		if (cxt == null)
			cxt = context;
		telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
	}
//	 /*
//	   * 电话状态：
//	   * 1.tm.CALL_STATE_IDLE=0          无活动
//	   * 2.tm.CALL_STATE_RINGING=1  响铃
//	   * 3.tm.CALL_STATE_OFFHOOK=2  摘机
//	   */
//	  tm.getCallState();//int
//	  
//	  /*
//	   * 电话方位：
//	   * 
//	   */
//	  tm.getCellLocation();//CellLocation
//	  
//	  /*
//	   * 唯一的设备ID：
//	   * GSM手机的 IMEI 和 CDMA手机的 MEID. 
//	   * Return null if device ID is not available.
//	   */
//	  tm.getDeviceId();//String
//	  
//	  /*
//	   * 设备的软件版本号：
//	   * 例如：the IMEI/SV(software version) for GSM phones.
//	   * Return null if the software version is not available. 
//	   */
//	  tm.getDeviceSoftwareVersion();//String
//	  
//	  /*
//	   * 手机号：
//	   * GSM手机的 MSISDN.
//	   * Return null if it is unavailable. 
//	   */
//	  tm.getLine1Number();//String
//	  
//	  /*
//	   * 附近的电话的信息:
//	   * 类型：List<NeighboringCellInfo> 
//	   * 需要权限：android.Manifest.permission#ACCESS_COARSE_UPDATES
//	   */
//	  tm.getNeighboringCellInfo();//List<NeighboringCellInfo>
//	  
//	  /*
//	   * 获取ISO标准的国家码，即国际长途区号。
//	   * 注意：仅当用户已在网络注册后有效。
//	   *       在CDMA网络中结果也许不可靠。
//	   */
//	  tm.getNetworkCountryIso();//String
//	  
//	  /*
//	   * MCC+MNC(mobile country code + mobile network code)
//	   * 注意：仅当用户已在网络注册时有效。
//	   *    在CDMA网络中结果也许不可靠。
//	   */
//	  tm.getNetworkOperator();//String
//	  
//	  /*
//	   * 按照字母次序的current registered operator(当前已注册的用户)的名字
//	   * 注意：仅当用户已在网络注册时有效。
//	   *    在CDMA网络中结果也许不可靠。
//	   */
//	  tm.getNetworkOperatorName();//String
//	  
//	  /*
//	   * 当前使用的网络类型：
//	   * 例如： NETWORK_TYPE_UNKNOWN  网络类型未知  0
//	     NETWORK_TYPE_GPRS     GPRS网络  1
//	     NETWORK_TYPE_EDGE     EDGE网络  2
//	     NETWORK_TYPE_UMTS     UMTS网络  3
//	     NETWORK_TYPE_HSDPA    HSDPA网络  8 
//	     NETWORK_TYPE_HSUPA    HSUPA网络  9
//	     NETWORK_TYPE_HSPA     HSPA网络  10
//	     NETWORK_TYPE_CDMA     CDMA网络,IS95A 或 IS95B.  4
//	     NETWORK_TYPE_EVDO_0   EVDO网络, revision 0.  5
//	     NETWORK_TYPE_EVDO_A   EVDO网络, revision A.  6
//	     NETWORK_TYPE_1xRTT    1xRTT网络  7
//	   */
//	  tm.getNetworkType();//int
//	  
//	  /*
//	   * 手机类型：
//	   * 例如： PHONE_TYPE_NONE  无信号
//	     PHONE_TYPE_GSM   GSM信号
//	     PHONE_TYPE_CDMA  CDMA信号
//	   */
//	  tm.getPhoneType();//int
//	  
//	  /*
//	   * Returns the ISO country code equivalent for the SIM provider's country code.
//	   * 获取ISO国家码，相当于提供SIM卡的国家码。
//	   * 
//	   */
//	  tm.getSimCountryIso();//String
//	  
//	  /*
//	   * Returns the MCC+MNC (mobile country code + mobile network code) of the provider of the SIM. 5 or 6 decimal digits.
//	   * 获取SIM卡提供的移动国家码和移动网络码.5或6位的十进制数字.
//	   * SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断).
//	   */
//	  tm.getSimOperator();//String
//	  
//	  /*
//	   * 服务商名称：
//	   * 例如：中国移动、联通
//	   * SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断).
//	   */
//	  tm.getSimOperatorName();//String
//	  
//	  /*
//	   * SIM卡的序列号：
//	   * 需要权限：READ_PHONE_STATE
//	   */
//	  tm.getSimSerialNumber();//String
//	  
//	  /*
//	   * SIM的状态信息：
//	   *  SIM_STATE_UNKNOWN          未知状态 0
//	   SIM_STATE_ABSENT           没插卡 1
//	   SIM_STATE_PIN_REQUIRED     锁定状态，需要用户的PIN码解锁 2
//	   SIM_STATE_PUK_REQUIRED     锁定状态，需要用户的PUK码解锁 3
//	   SIM_STATE_NETWORK_LOCKED   锁定状态，需要网络的PIN码解锁 4
//	   SIM_STATE_READY            就绪状态 5
//	   */
//	  tm.getSimState();//int
//	  
//	  /*
//	   * 唯一的用户ID：
//	   * 例如：IMSI(国际移动用户识别码) for a GSM phone.
//	   * 需要权限：READ_PHONE_STATE
//	   */
//	  tm.getSubscriberId();//String
//	  
//	  /*
//	   * 取得和语音邮件相关的标签，即为识别符
//	   * 需要权限：READ_PHONE_STATE
//	   */
//	  tm.getVoiceMailAlphaTag();//String
//	  
//	  /*
//	   * 获取语音邮件号码：
//	   * 需要权限：READ_PHONE_STATE
//	   */
//	  tm.getVoiceMailNumber();//String
//	  
//	  /*
//	   * ICC卡是否存在
//	   */
//	  tm.hasIccCard();//boolean
//	  
//	  /*
//	   * 是否漫游:
//	   * (在GSM用途下)
//	   */
//	  tm.isNetworkRoaming();//
	/**
	 * Role:获取当前设置的电话号码 <BR>
	 * Date:2012-3-12 <BR>
	 * 
	 * @author CODYY)peijiangping
	 */
	public String getNativePhoneNumber() {
		String NativePhoneNumber = null;
		NativePhoneNumber = telephonyManager.getLine1Number();
		return NativePhoneNumber;
	}

	/**
	 * Role:Telecom service providers获取手机服务商信息 <BR>
	 * 需要加入权限<uses-permission
	 * android:name="android.permission.READ_PHONE_STATE"/> <BR>
	 * Date:2012-3-12 <BR>
	 * 
	 * @author CODYY)peijiangping
	 */
	public String getProvidersName() {
		String ProvidersName = null;
		// 返回唯一的用户ID;就是这张卡的编号神马的
		IMSI = telephonyManager.getSubscriberId();
		// IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
		System.out.println(IMSI);
		if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
			ProvidersName = "中国移动";
		} else if (IMSI.startsWith("46001")) {
			ProvidersName = "中国联通";
		} else if (IMSI.startsWith("46003")) {
			ProvidersName = "中国电信";
		}
		return ProvidersName;
	}
}
