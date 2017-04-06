package com.example.attedance;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import com.example.utils.ActivityCollector;
import com.example.utils.HttpGetUtil;
import com.example.utils.HttpPostVerifyQR;
import com.example.utils.ServerAddressUtil;
//import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StudentActivity extends BaseActivity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.student_layout);
	    Button scanQrBtn = (Button) findViewById(R.id.scan_qr_btn);
	    Button testQrBtn = (Button) findViewById(R.id.test_btn);
	    Button logoutBtn = (Button) findViewById(R.id.logout_btn);
	    scanQrBtn.setOnClickListener(this);
	    testQrBtn.setOnClickListener(this);
	    logoutBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.scan_qr_btn){
			//scan
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();
		}else if(v.getId() == R.id.test_btn){
			sendVerifyQRCode("1:abcd");
		}else if(v.getId() == R.id.logout_btn){
			ActivityCollector.logout();
		}
		
	}
	
	public void sendVerifyQRCode(String str){
		Intent intentFromLogin = getIntent();
		String token = intentFromLogin.getStringExtra("token");
		String id = intentFromLogin.getStringExtra("id");
		 List<NameValuePair> params = new ArrayList<NameValuePair>();
		 params.add(new BasicNameValuePair("qrcode",str));
		 params.add(new BasicNameValuePair("accountid",id));
		 Log.d("qrcode", str);
		 Log.d("accountid", id);
		 UrlEncodedFormEntity entity = null;
		try {
			entity = new UrlEncodedFormEntity(params, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Object[] paramsList = new Object[3];
		 paramsList[0] = ServerAddressUtil.verifyQRCodeUrl;
		 paramsList[1] = entity;
		 paramsList[2] = token;
		 HttpPostVerifyQR httpPostVerifyQR = new HttpPostVerifyQR(StudentActivity.this);
		 httpPostVerifyQR.execute(paramsList);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		//retrieve scan result
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (scanningResult != null) {
			//we have a result
			String scanFormat = scanningResult.getFormatName();
			String scanContent = scanningResult.getContents();
			sendVerifyQRCode(scanContent);
			Toast toast = Toast.makeText(getApplicationContext(), 
		            scanContent, Toast.LENGTH_SHORT);
		        toast.show();
			Log.d("SCAN QR CODE", scanContent);
		}else{
		    Toast toast = Toast.makeText(getApplicationContext(), 
		            "No scan data received!", Toast.LENGTH_SHORT);
		        toast.show();
		}
	}

	
	

}