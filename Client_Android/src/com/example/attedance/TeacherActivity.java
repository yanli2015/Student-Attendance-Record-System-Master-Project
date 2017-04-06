package com.example.attedance;

import java.io.File;

import com.example.qrcode.QRCode;
import com.example.utils.ActivityCollector;
import com.example.utils.HttpGetUtil;
import com.example.utils.ServerAddressUtil;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class TeacherActivity extends BaseActivity {
	
	ImageView imageView =null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher_layout);
		setImageView((ImageView) findViewById(R.id.get_qr_iv));
		//imageView = getImageView();
		Intent intent = getIntent();
		final String token = intent.getStringExtra("token");
  
        Button createQrBtn = (Button) findViewById(R.id.get_qr_btn);
        
        Button logoutBtn = (Button) findViewById(R.id.logout_btn);
        
        createQrBtn.setOnClickListener(new View.OnClickListener() {
        	String qrCodeString = null;
            @Override
            public void onClick(View v) {
            	Object[] paramsList = new Object[2];
            	paramsList[0] = ServerAddressUtil.getQRCodeUrl;
            	paramsList[1] = token;
            	HttpGetUtil httpGetUtil = new HttpGetUtil(TeacherActivity.this, TeacherActivity.this);
            	httpGetUtil.execute(paramsList);
            }
        });
        
        logoutBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ActivityCollector.logout();
				
			}
		});
    }
 
    public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
