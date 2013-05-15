package cn.wang.yin.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonDbAdapter extends SQLiteOpenHelper {
	public PersonDbAdapter(Context context) {
		super(context, PersonConstant.DB_NAME, null, 1);

	}

	public PersonDbAdapter(Context context, String name, CursorFactory factory,
			int version) {
		super(context, PersonConstant.DB_NAME, null, 1);

	}

	public void execSQL(){
		
	}
	@Override
	public void onCreate(SQLiteDatabase arg0) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
