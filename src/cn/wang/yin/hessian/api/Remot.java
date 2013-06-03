package cn.wang.yin.hessian.api;

import java.io.File;
import java.util.List;

import cn.shui.express.scan.hessian.bean.Express;
import cn.shui.express.scan.hessian.bean.GpsInfo;
import cn.shui.express.scan.hessian.bean.PhoneInfo;

public interface Remot {
	// public User Hession(String name);

	public String Test(String name);

	/**
	 * 单个上传GPS数据
	 * 
	 * @param gps
	 * @return
	 */
	public boolean uploadGps(GpsInfo gps);

	/**
	 * 批量上传
	 * 
	 * @param listGps
	 * @return
	 */
	public int uploadGps(List<GpsInfo> listGps);

	public String mm();

	public String saveFile(File file);

	/**
	 * 上传用户手机信息
	 * 
	 * @param phone 手机信息
	 * @return
	 */
	public int uploadPhoneInfo(PhoneInfo phone);

	/**
	 * 查询快递码
	 * 
	 * @param expressNum 快递单号
	 * 
	 * @return 快递单详情
	 */
	public Express scanExpress(String expressNum);
	
	/**
	 * 查询快递单的快递企业名
	 * @param expressNum 快递单号
	 * @return 快递名称(汉字)
	 */
	public String findExpressName(String expressNum);
	/**
	 * 查询快递单的快递企业名
	 * @param expressNum 快递单号
	 * @return 快递名称(拼音)
	 */
	public String findExpressNamePinYin(String expressNum);
}
