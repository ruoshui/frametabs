package cn.wang.yin.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang.RandomStringUtils;

import cn.wang.yin.personal.R;
import cn.wang.yin.utils.PersonStringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class SaveImageFragment extends Fragment {
	TextView textView1;
	Button button1;
	SeekBar seekBar1;
	ImageView imageView1;
	static int width;
	static int height;
	int strLen = 15;
	private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		if (container == null) {
			return null;
		}

		LayoutInflater myInflater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = myInflater.inflate(R.layout.save, container, false);
		textView1 = (TextView) layout.findViewById(R.id.textView1);
		button1 = (Button) layout.findViewById(R.id.button1);
		// Thread t = new Thread(saverun);
		// t.run();
		seekBar1 = (SeekBar) layout.findViewById(R.id.seekBar1);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Thread t = new Thread(saverun);
				t.run();
			}
		});
		imageView1 = (ImageView) layout.findViewById(R.id.imageView1);
		width = imageView1.getWidth();
		height = imageView1.getHeight();
		seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				strLen = progress > 10 ? progress * 10 : 10;
				// ggg
				push("当前长度为：" + strLen);
			}
		});

		return layout;
	}

	Handler hand = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0: {
				if (msg.obj != null)
					push(msg.obj.toString());
			}
				break;
			case 1: {
				if (msg.obj != null) {
					String p = msg.obj.toString();
					String myJpgPath = p;
					BitmapFactory.Options options = new BitmapFactory.Options();
					options.inSampleSize = 2;
					Bitmap bm = BitmapFactory.decodeFile(myJpgPath, options);
					imageView1.setImageBitmap(bm);
				}
			}
				break;
			}

			super.handleMessage(msg);
		}

	};

	Runnable saverun = new Runnable() {
		@Override
		public void run() {
			Message msg = new Message();
			msg.what = 0;
			Bitmap bp = null;
			try {

				bp = encodeAsBitmap(RandomStringUtils.randomNumeric(strLen),
						BarcodeFormat.QR_CODE, 300, 300);
				// width >= height ? height : width,
				// width >= height ? height : width);
			} catch (WriterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// bp=Create2DCode(RandomStringUtils.randomNumeric(strLen));
			long timestamp = System.currentTimeMillis();
			// String time = formatter.format(new Date());
			String path = "/sdcard/myimage/";
			File p = new File(path);
			if (!p.exists()) {
				p.mkdirs();
			}
			String fileName = timestamp + ".png";
			File f = new File(path + fileName);
			try {
				if (f.createNewFile()) {
					FileOutputStream fos = new FileOutputStream(f);
					bp.compress(Bitmap.CompressFormat.PNG, 100, fos);
					fos.close();
					msg.obj = "存储成功,字符长度为：" + strLen + "\n路径为\t" + f.getPath();
					Message message = new Message();
					message.what = 1;
					message.obj = f.getPath();
					hand.sendMessage(message);
				} else {
					msg.obj = "文件没有创建成功!";
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				msg.obj = "失败!\t" + e.getMessage();
			}

			hand.sendMessage(msg);

		}
	};

	/**
	 * 用字符串生成二维码
	 * 
	 * @param str
	 * @author zhouzhe@lenovo-cw.com
	 * @return
	 * @throws WriterException
	 */
	public Bitmap Create2DCode(String str) {
		Message msg = new Message();
		msg.what = 0;
		// 生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
		BitMatrix matrix = null;
		try {
			matrix = new MultiFormatWriter().encode(str,
					BarcodeFormat.CODE_128, 200, 200);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		// 二维矩阵转为一维像素数组,也就是一直横着排了
		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (matrix.get(x, y)) {
					pixels[y * width + x] = 0xff000000;
				} else {
					pixels[y * width + x] = 0xFFFFFFFF;
				}

			}
		}

		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		// 通过像素数组生成bitmap,具体参考api
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

	public void push(String text) {
		textView1.setText(text + "\n"
				+ PersonStringUtils.pareDateToString(new Date())
				+ "\n----------------------------------------------\n"
				+ textView1.getText());
	}

	static Bitmap encodeAsBitmap(String contents, BarcodeFormat format,
			int desiredWidth, int desiredHeight) throws WriterException {
		final int WHITE = 0xFFFFFFFF; // 可以指定其他颜色，让二维码变成彩色效果
		final int BLACK = 0xFF000000;

		HashMap<EncodeHintType, String> hints = null;
		String encoding = guessAppropriateEncoding(contents);
		if (encoding != null) {
			hints = new HashMap<EncodeHintType, String>(2);
			hints.put(EncodeHintType.CHARACTER_SET, encoding);
		}
		MultiFormatWriter writer = new MultiFormatWriter();
		BitMatrix result = writer.encode(contents, format, desiredWidth,
				desiredHeight, hints);
		int width = result.getWidth();
		int height = result.getHeight();
		int[] pixels = new int[width * height];
		// All are 0, or black, by default
		for (int y = 0; y < height; y++) {
			int offset = y * width;
			for (int x = 0; x < width; x++) {
				pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
			}
		}

		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

	private static String guessAppropriateEncoding(CharSequence contents) {
		// Very crude at the moment
		for (int i = 0; i < contents.length(); i++) {
			if (contents.charAt(i) > 0xFF) {
				return "UTF-8";
			}
		}
		return null;
	}
}
