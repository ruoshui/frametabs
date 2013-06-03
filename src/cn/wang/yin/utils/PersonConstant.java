package cn.wang.yin.utils;

public class PersonConstant {
	public static final String DB_NAME = "persinal";
	// private float gpsSpeed;
	// private Integer gpsSatelliteNumber;
	// private float gpsRadius;
	public static final String SQL_GPS_INFO = "create table  if not exists gps_info (  "
			+ " t_id integer primary key   autoincrement ,  "
			+ " t_time varchar(30) ,"
			+ " t_loctype varchar(30),"
			+ " t_latitude varchar(30),"
			+ " t_lontitude  varchar(30),"
			+ " t_address varchar(50),"
			+ " t_writetime  varchar(30),t_radius varchar(30),"
			+ "gpsSpeed real,gpsSatelliteNumber int );";

	// 表一：etong_user
	public static final String SQL_ETONG_USER = "create table if not exists personal_users("
			+ " u_id integer primary key autoincrement,"
			+ " u_username varchar(30),"
			+ "	u_phonenumber varchar(30),"
			+ " u_odbnumber varchar(30),"
			+ " u_password varchar(30),"
			+ " u_image varchar(30), "
			+ " u_sinaweibo int,"
			+ " u_tencentweibo int,"
			+ " u_tencentqq int,"
			+ " u_carnumber varchar(30),"
			+ " u_vehicleType varchar(30),"
			+ " u_remark text ," + "u_deviceid  varchar(30) );";
	public static final String SQL_LOGIN_OUT = "update  personal_users  set [u_password]=null  where [u_phonenumber]=?;";
	public static final String CHECK_FAIL = "请检查用户名密码！";
	public static final String CHECK_SUCCESS = "登录成功";
	/**
	 * 测试数据
	 */
	public static final String DROP_SQL_ETONG_TRAVELMANAGEMENT = "drop table if exists etong_travelmanagement";
	public static final String DROP_SQL_ETONG_USER = "drop table if exists etong_users";

	/**
	 * 接口地址
	 */
	public static final String REMOTE_URL = "http://3.myexpress.duapp.com/hapi";
	// public static final String REMOTE_URL
	// ="http://192.168.2.185:8080/th/hapi";
	public static final long WAIT_TIMS = 1000 * 30;
	public static final long UPLOAD_TIMS = WAIT_TIMS * 3;
	public static final long SLEEP_TIMS = 1000 * 1;
	public static final String PREFERENCES_FILE_NAME = "personal_preferences";

	public static final String USER_NAME = "username";
	public static final String PASS_WORD = "password";

	public static final String SQL_PERSON_COLLECT = "create table  if not exists person_collect (  "
			+ " id integer primary key   autoincrement ,  "
			+ " message varchar(100) ,"
			+ " exceptiontype varchar(200),"
			+ " exlocation varchar(100),"
			+ " phonenum varchar(20),"
			+ " pruducttime varchar(30));";
	public static final String MSEESGE_REMIND_TICKER = "推送的通知";
	public static final String MSEESGE_REMIND_CONTENT = "通知内容";
	public static final String BAIDU_MAP_KEY = "8BDDCA6948E6F5489859BBCBA3B577139A45FD1D";
	public static final int COMMON_NOTIFICATION = 1;
	public static final String API_KEY = "ME5ObDvVpp5SsofXTPWiqdzr";

	public static final String USER_AGENT_INFO = "user_agent_info_cn.wang.yin";
	public static final String USER_AGENT_INFO_CALLSTATE = "user_agent_info_callstate_cn.wang.yin";
	public static final String USER_AGENT_INFO_CELLLOCATION = "user_agent_info_celllocation_cn.wang.yin";
	public static final String USER_AGENT_INFO_CALLIMEI = "user_agent_info_callimei_cn.wang.yin";
	public static final String USER_AGENT_INFO_CALLMSISDN = "user_agent_info_callmsisdn_cn.wang.yin";
	public static final String USER_AGENT_INFO_CALLNETWORKCOUNTRYISO = "user_agent_info_callnetworkcountryiso_cn.wang.yin";
	public static final String USER_AGENT_INFO_CALLNETWORKOPERATOR = "user_agent_info_callnetworkoperator_cn.wang.yin";
	public static final String USER_AGENT_INFO_CALLNETWORKOPERATORNAME = "user_agent_info_callnetworkoperatorname_cn.wang.yin";
	public static final String USER_AGENT_INFO_CALLNETWORKTYPE = "user_agent_info_callnetworktype_cn.wang.yin";
	public static final String USER_AGENT_INFO_CALLPHONETYPE = "user_agent_info_callphonetype_cn.wang.yin";
	public static final String USER_AGENT_INFO_CALLSIMOPERATOR = "user_agent_info_callsimoperator_cn.wang.yin";
	public static final String USER_AGENT_INFO_CALLSIMSTATE = "user_agent_info_callsimstate_cn.wang.yin";
	public static final String USER_AGENT_INFO_BDUID = "user_agent_info_bduid_cn.wang.yin";
	public static final String USER_AGENT_INFO_PHONENUM = "user_agent_info_phonenum_cn.wang.yin";

	public static final String USER_AGENT_UPLOADED = "user_agent_info_uploaded_cn.wang.yin";

	// 测试俩人同时上传
	public static final String LOCATION_CHANGE_TAG = "person_location_change";

	public static final int LOCATION_CHANGE = 23;
	public static final String USER_FIRST_OPEN = "user_agent_info_first_open_cn.wang.yin";
	public static final int ETONG_RESULTCODE_INDEX = 110;
	
	
	

}
