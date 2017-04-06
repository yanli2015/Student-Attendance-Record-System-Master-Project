package com.example.attedance;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.example.bean.Account;
import com.example.utils.HttpPostUtil;
import com.example.utils.ServerAddressUtil;
import com.google.gson.Gson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity{
	
	 private EditText accountEdit;
	 private EditText passwordEdit;
	 private Boolean isTeacher;
	 private Button login;
	 private Button signup;
	 private CheckBox cb;
	 
	 @Override
	 protected void onCreate(Bundle savedInstanceState){
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.login);
		 accountEdit = (EditText) findViewById(R.id.account);
		 passwordEdit = (EditText) findViewById(R.id.password);
		 login = (Button) findViewById(R.id.login);
		 signup = (Button) findViewById(R.id.signup);
		 CheckBox cb = (CheckBox)this.findViewById(R.id.cb);
		 
		 login.setOnClickListener(new OnClickListener(){
			 
			 @Override
			 public void onClick(View v){
				 String account = accountEdit.getText().toString();
				 String password = passwordEdit.getText().toString();
				 List<NameValuePair> params = new ArrayList<NameValuePair>();
				 params.add(new BasicNameValuePair("username",account));
				 params.add(new BasicNameValuePair("password",password));
				 Log.d("LoginAcitivity", account);
				 try {
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");
					Object[] paramsList = new Object[2];
					paramsList[0]=ServerAddressUtil.siginUrl;
					paramsList[1]=entity;
				//	paramsList[2] = this;
					HttpPostUtil httpPostUtil = new HttpPostUtil(LoginActivity.this);
					httpPostUtil.execute(paramsList);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			 }
			 
			 
		 });
		 
		 
		 cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		             
		             @Override
		             public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		 
		                 // TODO Auto-generated method stub
		            	 isTeacher = arg1;
		                 Toast.makeText(LoginActivity.this, 
		                         arg1?"selected":"unselected"    , Toast.LENGTH_LONG).show();
		             }
		         });
		 
		 signup.setOnClickListener(new OnClickListener(){
			 
			 @Override
			 public void onClick(View v){
				 String username = accountEdit.getText().toString();
				 String password = passwordEdit.getText().toString();
				 List<NameValuePair> params = new ArrayList<NameValuePair>();
				 params.add(new BasicNameValuePair("username",username));
				 params.add(new BasicNameValuePair("password",password));
				 params.add(new BasicNameValuePair("isTeacher", isTeacher.toString()));
				 Log.d("LoginAcitivity", username);
				 try {
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");
					Object[] paramsList = new Object[2];
					paramsList[0]=ServerAddressUtil.signupUrl;
					paramsList[1]=entity;
				//	paramsList[2] = this;
					HttpPostUtil httpPostUtil = new HttpPostUtil(LoginActivity.this);
					httpPostUtil.execute(paramsList);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			 }
			 
			 
		 });
	}

}
	 
	 
	
	


