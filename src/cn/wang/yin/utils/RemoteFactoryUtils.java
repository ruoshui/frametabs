package cn.wang.yin.utils;


import cn.wang.yin.hessian.api.Remot;

import com.caucho.hessian.client.HessianProxyFactory;

public class RemoteFactoryUtils {
	private static HessianProxyFactory factory = null;
	private static Remot remot;

	public static HessianProxyFactory getFactory() {
		if (factory == null) {
			factory = new HessianProxyFactory();
			factory.setOverloadEnabled(true);
			factory.setHessian2Reply(false);
			factory.setChunkedPost(false);

		}
		return factory;
	}

	public static Remot getReport() {
		if (remot == null) {
			try {
				remot = getFactory().create(Remot.class,
						PersonConstant.REMOTE_URL);
			} catch (Exception e) {
				CollectDebugLogUtil.saveDebug(e.getMessage(), e.getClass()
						.toString(),
						"cn.etgps.etong.utils.RemoteFactoryUtils.getReport()");
			}

		}
		return remot;
	}
}
