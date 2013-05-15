package com.wang.yin.hessian.bean;

import java.util.Date;

/**
 * GpsInfo entity. @author MyEclipse Persistence Tools
 */
public class GpsInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Date gpsTime;
	private Date gpsWriteTime;
	private Integer errorCode;
	private double gpsLatitude;
	private double gpsLongitude;
	private String gpsLocation;
	private String gpsAddrStr;
	private String gpsType;
	private float gpsSpeed;
	private Integer gpsSatelliteNumber;
	private float gpsRadius;
	private String bdUid;

	// Constructors

	/** default constructor */
	public GpsInfo() {
	}

	/** full constructor */
	public GpsInfo(Date gpsTime, Date gpsWriteTime, Integer errorCode,
			double gpsLatitude, double gpsLongitude, String gpsLocation,
			String gpsAddrStr, String gpsType, float gpsSpeed,
			Integer gpsSatelliteNumber, float gpsRadius, String bdUid) {
		this.gpsTime = gpsTime;
		this.gpsWriteTime = gpsWriteTime;
		this.errorCode = errorCode;
		this.gpsLatitude = gpsLatitude;
		this.gpsLongitude = gpsLongitude;
		this.gpsLocation = gpsLocation;
		this.gpsAddrStr = gpsAddrStr;
		this.gpsType = gpsType;
		this.gpsSpeed = gpsSpeed;
		this.gpsSatelliteNumber = gpsSatelliteNumber;
		this.gpsRadius = gpsRadius;
		this.bdUid = bdUid;
	}

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getGpsTime() {
		return this.gpsTime;
	}

	public void setGpsTime(Date gpsTime) {
		this.gpsTime = gpsTime;
	}

	public Date getGpsWriteTime() {
		return this.gpsWriteTime;
	}

	public void setGpsWriteTime(Date gpsWriteTime) {
		this.gpsWriteTime = gpsWriteTime;
	}

	public Integer getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public double getGpsLatitude() {
		return this.gpsLatitude;
	}

	public void setGpsLatitude(double gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}

	public double getGpsLongitude() {
		return this.gpsLongitude;
	}

	public void setGpsLongitude(double gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}

	public String getGpsLocation() {
		return this.gpsLocation;
	}

	public void setGpsLocation(String gpsLocation) {
		this.gpsLocation = gpsLocation;
	}

	public String getGpsAddrStr() {
		return this.gpsAddrStr;
	}

	public void setGpsAddrStr(String gpsAddrStr) {
		this.gpsAddrStr = gpsAddrStr;
	}

	public String getGpsType() {
		return this.gpsType;
	}

	public void setGpsType(String gpsType) {
		this.gpsType = gpsType;
	}

	public float getGpsSpeed() {
		return this.gpsSpeed;
	}

	public void setGpsSpeed(float gpsSpeed) {
		this.gpsSpeed = gpsSpeed;
	}

	public Integer getGpsSatelliteNumber() {
		return this.gpsSatelliteNumber;
	}

	public void setGpsSatelliteNumber(Integer gpsSatelliteNumber) {
		this.gpsSatelliteNumber = gpsSatelliteNumber;
	}

	public float getGpsRadius() {
		return this.gpsRadius;
	}

	public void setGpsRadius(float gpsRadius) {
		this.gpsRadius = gpsRadius;
	}

	public String getBdUid() {
		return this.bdUid;
	}

	public void setBdUid(String bdUid) {
		this.bdUid = bdUid;
	}

}