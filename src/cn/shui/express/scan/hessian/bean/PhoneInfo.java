package cn.shui.express.scan.hessian.bean;


/**
 * PhoneInfo entity. @author MyEclipse Persistence Tools
 */
public class PhoneInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer callState;
	private String cellLocation;
	private String callImei;
	private String callMsisdn;
	private String callNetworkCountryIso;
	private String callNetworkOperator;
	private String callNetworkOperatorName;
	private Integer callNetworkType;
	private Integer callPhoneType;
	private String callSimOperator;
	private Integer callSimState;
	private String bdUid;
	private String phonenum;
	// Constructors
	 // txtPhoneModel.setText(Build.MODEL); //手机型号
	  //txtPhoneNumber.setText(phoneMgr.getLine1Number());//本机电话号码
	  //txtSdkVersion.setText(Build.VERSION.SDK);//SDK版本�?	  //txtOsVersion.setText(Build.VERSION.RELEASE);//Firmware/OS 版本�?	/** default constructor */
	public PhoneInfo() {
	}

	/** full constructor */
	public PhoneInfo(Integer callState, String cellLocation, String callImei,
			String callMsisdn, String callNetworkCountryIso,
			String callNetworkOperator, String callNetworkOperatorName,
			Integer callNetworkType, Integer callPhoneType,
			String callSimOperator, Integer callSimState, String bdUid,String phonenum) {
		this.callState = callState;
		this.cellLocation = cellLocation;
		this.callImei = callImei;
		this.callMsisdn = callMsisdn;
		this.callNetworkCountryIso = callNetworkCountryIso;
		this.callNetworkOperator = callNetworkOperator;
		this.callNetworkOperatorName = callNetworkOperatorName;
		this.callNetworkType = callNetworkType;
		this.callPhoneType = callPhoneType;
		this.callSimOperator = callSimOperator;
		this.callSimState = callSimState;
		this.bdUid = bdUid;
      	this.phonenum=phonenum;
	}

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCallState() {
		return this.callState;
	}

	public void setCallState(Integer callState) {
		this.callState = callState;
	}

	public String getCellLocation() {
		return this.cellLocation;
	}

	public void setCellLocation(String cellLocation) {
		this.cellLocation = cellLocation;
	}

	public String getCallImei() {
		return this.callImei;
	}

	public void setCallImei(String callImei) {
		this.callImei = callImei;
	}

	public String getCallMsisdn() {
		return this.callMsisdn;
	}

	public void setCallMsisdn(String callMsisdn) {
		this.callMsisdn = callMsisdn;
	}

	public String getCallNetworkCountryIso() {
		return this.callNetworkCountryIso;
	}

	public void setCallNetworkCountryIso(String callNetworkCountryIso) {
		this.callNetworkCountryIso = callNetworkCountryIso;
	}

	public String getCallNetworkOperator() {
		return this.callNetworkOperator;
	}

	public void setCallNetworkOperator(String callNetworkOperator) {
		this.callNetworkOperator = callNetworkOperator;
	}

	public String getCallNetworkOperatorName() {
		return this.callNetworkOperatorName;
	}

	public void setCallNetworkOperatorName(String callNetworkOperatorName) {
		this.callNetworkOperatorName = callNetworkOperatorName;
	}

	public Integer getCallNetworkType() {
		return this.callNetworkType;
	}

	public void setCallNetworkType(Integer callNetworkType) {
		this.callNetworkType = callNetworkType;
	}

	public Integer getCallPhoneType() {
		return this.callPhoneType;
	}

	public void setCallPhoneType(Integer callPhoneType) {
		this.callPhoneType = callPhoneType;
	}

	public String getCallSimOperator() {
		return this.callSimOperator;
	}

	public void setCallSimOperator(String callSimOperator) {
		this.callSimOperator = callSimOperator;
	}

	public Integer getCallSimState() {
		return this.callSimState;
	}

	public void setCallSimState(Integer callSimState) {
		this.callSimState = callSimState;
	}

	public String getBdUid() {
		return this.bdUid;
	}

	public void setBdUid(String bdUid) {
		this.bdUid = bdUid;
	}
	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
}