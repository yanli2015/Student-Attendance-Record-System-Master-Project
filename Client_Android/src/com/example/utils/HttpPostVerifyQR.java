package com.example.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.example.attedance.StudentActivity;
import com.example.attedance.TeacherActivity;
import com.example.bean.Account;
import com.google.gson.Gson;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class HttpPostVerifyQR extends AsyncTask<Object, Integer, Integer>{
	
	Context myContext = null;
	
	public HttpPostVerifyQR(Context ctx){
		myContext = ctx;
	}

	@Override
	protected Integer doInBackground(Object... params) {
		// TODO Auto-generated method stub
		
		    HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost((String)params[0]);
			httpPost.setEntity((UrlEncodedFormEntity)params[1]);
			Header header = new BasicHeader("AUTHORIZATION", "Bearer " +(String)params[2]);
			Log.d("Auth", header.getName());
			Log.d("Value", header.getValue());
			httpPost.setHeader(header);
			
			HttpResponse response = null;
			try {
				response = httpClient.execute(httpPost);
				Log.d("StudentAcitivity", response.getStatusLine().getStatusCode()+"");
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return response.getStatusLine().getStatusCode();
	}
	@Override
	protected void onPostExecute(Integer status){
		if( status == 202){
			Toast toast = Toast.makeText(myContext.getApplicationContext(), 
		            "Your record has been updated sucessfully", Toast.LENGTH_SHORT);
		     toast.show();
		}
	}
}
