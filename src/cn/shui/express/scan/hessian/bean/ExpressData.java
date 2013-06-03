package cn.shui.express.scan.hessian.bean;

import java.util.Date;

/**
 * ExpressData entity. @author MyEclipse Persistence Tools
 */
public class ExpressData implements java.io.Serializable {

	// Fields

	private Integer id;
	private Express express;
	private Date time;
	private String context;
	private Date ftime;

	// Constructors

	/** default constructor */
	public ExpressData() {
	}

	/** full constructor */
	public ExpressData(Express express, Date time, String context,
			Date ftime) {
		this.express = express;
		this.time = time;
		this.context = context;
		this.ftime = ftime;
	}

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Express getExpress() {
		return this.express;
	}

	public void setExpress(Express express) {
		this.express = express;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getFtime() {
		return this.ftime;
	}

	public void setFtime(Date ftime) {
		this.ftime = ftime;
	}

}