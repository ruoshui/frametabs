package cn.shui.express.scan.hessian.bean;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Express entity. @author MyEclipse Persistence Tools
 */
public class Express implements java.io.Serializable {

	// Fields

	private Integer id;
	private String message;
	private String nu;
	private Integer ischeck;
	private String com;
	private Date updatetime;
	/**
	 * 200 投�?成功<br>
	 * 201 快�?公司参数异常：单号不存在或�?已经过期
	 */
	private Integer status;
	private String condition;
	private String expressname;
	private Set<ExpressData> expressDatas = new LinkedHashSet<ExpressData>(0);

	// Constructors

	/** default constructor */
	public Express() {
	}

	/** full constructor */
	public Express(String message, String nu, Integer ischeck, String com,
			Date updatetime, Integer status, String condition,
			String expressname, Set<ExpressData> expressDatas) {
		this.message = message;
		this.nu = nu;
		this.ischeck = ischeck;
		this.com = com;
		this.updatetime = updatetime;
		this.status = status;
		this.condition = condition;
		this.expressname = expressname;
		this.expressDatas = expressDatas;
	}

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNu() {
		return this.nu;
	}

	public void setNu(String nu) {
		this.nu = nu;
	}

	public Integer getIscheck() {
		return this.ischeck;
	}

	public void setIscheck(Integer ischeck) {
		this.ischeck = ischeck;
	}

	public String getCom() {
		return this.com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	/**
	 * 
	 * @return 200 投�?成功<br>
	 *         201 快�?公司参数异常：单号不存在或�?已经过期<br>
	 */
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCondition() {
		return this.condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getExpressname() {
		return this.expressname;
	}

	public void setExpressname(String expressname) {
		this.expressname = expressname;
	}

	public Set<ExpressData> getExpressDatas() {
		return this.expressDatas;
	}

	public void setExpressDatas(Set<ExpressData> expressDatas) {
		this.expressDatas = expressDatas;
	}

}