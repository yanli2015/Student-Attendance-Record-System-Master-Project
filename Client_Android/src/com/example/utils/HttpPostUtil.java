package com.example.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.example.attedance.LoginActivity;
import com.example.attedance.StudentActivity;
import com.example.attedance.TeacherActivity;
import com.example.bean.Account;
import com.google.gson.Gson;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class HttpPostUtil extends AsyncTask<Object, Integer, String[]>{
	
	Context myContext = null;
	
	public HttpPostUtil(Context ctx){
		myContext = ctx;
	}

	@Override
	protected String[] doInBackground(Object... params) {
		// TODO Auto-generated method stub
		
		    HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost((String)params[0]);
			httpPost.setEntity((UrlEncodedFormEntity)params[1]);
			HttpResponse response = null;
			String[] result = new String[2];
			try {
				response = httpClient.execute(httpPost);
				int status = response.getStatusLine().getStatusCode();
				HttpEntity responseEntity = response.getEntity();
				String responseStr = EntityUtils.toString(responseEntity, "utf-8");
				result[0] = status + "";
				result[1] = responseStr;
			//	Log.d("LoginAcitivity", response.getStatusLine().getStatusCode()+"");
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return result;
	}
	@Override
	protected void onPostExecute(String[] results){
		int status = Integer.parseInt(results[0]);
		if(status == 200  || status == 201){
			
			String responseStr = results[1];
			try {
				
				Account acc = parseJSONAccount(responseStr);
				String accountInfo = acc.getToken()+","+ acc.getRole() + "," + acc.getId();
				Log.d("LoginAcitivity", accountInfo);
				save(accountInfo);
				
				String result = load("userInfo");
				String[] strs = result.split(",");
				if("TEACHER".equals(strs[1])){
					Intent intent = new Intent(myContext, TeacherActivity.class);
					intent.putExtra("token", strs[0]);
					intent.putExtra("id", strs[2]);
					myContext.startActivity(intent);
				}else if("STUDENT".equals(strs[1])){
					Intent intent = new Intent(myContext, StudentActivity.class);
					intent.putExtra("token", strs[0]);
					intent.putExtra("id", strs[2]);
					myContext.startActivity(intent);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(status == 500){
			 Toast.makeText(myContext, 
                     "Password is not correct", Toast.LENGTH_LONG).show();
		}else if(status == 403){
			Toast.makeText(myContext, 
                    "The account is already exist", Toast.LENGTH_LONG).show();
		}
	}
	
	 private Account parseJSONAccount(String response){
		 Gson gason = new Gson();
		 Account acc = gason.fromJson(response, Account.class);
		 return acc;
		 
	 }
	 
	 private void save(String str){
		 String fileName = "userInfo";
		 FileOutputStream out = null;
		 BufferedWriter bw = null;
		 try {
			out = myContext.openFileOutput(fileName, Context.MODE_PRIVATE);
			bw = new BufferedWriter(new OutputStreamWriter(out));
			bw.write(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		 
	 }
	 
	 private String load(String fileName){
			FileInputStream in = null;
			BufferedReader reader = null;
			String result = "";
			
			try {
				in = myContext.openFileInput(fileName);
				reader = new BufferedReader(new InputStreamReader(in));
				result = reader.readLine();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
}
