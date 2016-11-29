package com.example.sendmessagetest;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btnsend;
	private EditText edTnum,edTMess;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnsend=(Button)findViewById(R.id.btnsend);
		edTnum = (EditText) findViewById(R.id.edTnum);
		edTMess = (EditText) findViewById(R.id.edTMess);
		btnsend.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				String mobile =edTnum.getText().toString();
				String content =edTMess.getText().toString();
				SmsManager smsManager=SmsManager.getDefault();//获取短信管理器
				PendingIntent sentIntent=PendingIntent.getBroadcast(MainActivity.this, 0, new Intent(), 0);
				List<String>msgs=smsManager.divideMessage(content);//划分短信
				for(String msg:msgs){
					smsManager.sendTextMessage(mobile, null, msg, sentIntent, null);//逐条发送短信
				}
				Toast.makeText(MainActivity.this, "短信发送完成！",Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
