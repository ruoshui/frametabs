package cn.wang.yin.hessian.api;

import java.io.File;
import java.util.List;

import com.wang.yin.hessian.bean.Express;
import com.wang.yin.hessian.bean.GpsInfo;
import com.wang.yin.hessian.bean.PhoneInfo;

public interface Remot {
	// public User Hession(String name);

	public String Test(String name);

	/**
	 * 单个上传GPS数据
	 * @param gps
	 * @return
	 */
	public boolean uploadGps(GpsInfo gps);

	/**
	 * 批量上传
	 * @param listGps
	 * @return
	 */
	public int uploadGps(List<GpsInfo> listGps);

	public String mm();

	public String saveFile(File file);
	/**
	 * 上传用户手机信息
	 * @param phone
	 * @return
	 */
	public int uploadPhoneInfo(PhoneInfo phone);
	/**
	 * 查询快递单号
	 * @param expressNum
	 * @return
	 */
	public Express scanExpress(String expressNum);
}
