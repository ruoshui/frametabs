package cn.wang.yin.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.wang.yin.hessian.api.Remot;
import cn.wang.yin.personal.R;
import cn.wang.yin.utils.PersonConstant;
import cn.wang.yin.utils.RemoteFactoryUtils;

import com.caucho.hessian.client.HessianProxyFactory;
import com.wang.yin.hessian.bean.Express;
import com.wang.yin.hessian.bean.ExpressData;

public class express extends Activity {
	EditText editText1;
	Button button1;
	String num = "";
	private ProgressDialog p_dialog;
	LinearLayout express_list;
	public static final int SUCCESS = 101;
	public static final int FAIL = 102;
	List<String> all = new ArrayList();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.express);
		editText1 = (EditText) findViewById(R.id.editText1);
		editText1.setText("5045205409800");
		button1 = (Button) findViewById(R.id.button1);
		express_list = (LinearLayout) findViewById(R.id.express_list);
		
		p_dialog=new ProgressDialog(getApplicationContext());
		p_dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		p_dialog.setMessage("载入中……");
		p_dialog.setTitle("请等待");
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String express_num = editText1.getText().toString();
				if (StringUtils.isNotBlank(express_num)) {
					num = express_num;
					//p_dialog.show();
					//p_dialog =ProgressDialog.show(express.this, "teee", "载入中……",true);
					Thread t = new Thread(submitRunnnable);
					t.run();
				}
			}
		});
	}

	public void fresh(List<ExpressData> datas) {

		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		express_list.removeAllViews();
		for (ExpressData bean : datas) {
			View child = inflater.inflate(R.layout.express_sinagle, null);
			TextView textView1 = (TextView) child.findViewById(R.id.textView1);
			ImageView imageView1 = (ImageView) child
					.findViewById(R.id.imageView1);
			TextView textView2 = (TextView) child.findViewById(R.id.textView2);
			textView1.setText("" + bean.getFtime());
			textView2.setText("" + bean.getContext());
			express_list.addView(child);
		}

	}

	Handler hand = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (p_dialog != null) {
				p_dialog.dismiss();
			}
			switch (msg.what) {
			case SUCCESS: {
				if (msg.obj != null) {
					List<ExpressData> datas = (List<ExpressData>) msg.obj;
					fresh(datas);
				}
			}
				break;
			case FAIL: {
				AlertDialog dialog = new AlertDialog.Builder(express.this)
						.setTitle("提示")
						.setMessage("需要网络，您的手机当前网络不可用，请设置您的网络")
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								}).create();
				dialog.show();
			}
				break;
			}

			super.handleMessage(msg);
		}

	};
	Runnable submitRunnnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			Message msg = new Message();
			msg.what = SUCCESS;
			Remot remot = null;
			try {
				// remot = RemoteFactoryUtils.getReport();
				HessianProxyFactory factory = RemoteFactoryUtils.getFactory();
				remot = factory.create(Remot.class, PersonConstant.REMOTE_URL);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				msg.what = FAIL;
				e.printStackTrace();
			}
			try {
				Express bean = remot.scanExpress(num);
				Log.e("gddddd", "测试");
				List<ExpressData> datas = bean.getData();
				msg.obj = datas;
			} catch (Exception e) {
				msg.what = FAIL;
				e.printStackTrace();
			}
			hand.sendMessage(msg);
		}
		
	};
	
	Runnable scanrunnable = new Runnable() {
		@Override
		public void run() {
		}
	};

}
