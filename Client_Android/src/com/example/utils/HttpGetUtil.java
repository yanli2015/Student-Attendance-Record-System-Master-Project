package com.example.utils;

import java.io.File;
import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.example.attedance.R;
import com.example.qrcode.QRCode;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

public class HttpGetUtil extends AsyncTask<Object, Integer, String[] > {
	
	private Context myContext = null;
	private Activity activitiy = null;
	public HttpGetUtil(Context ctx, Activity act){
		myContext = ctx;
		activitiy = act;
	}

	@Override
	protected String[] doInBackground(Object... params) {
		// TODO Auto-generated method stub
		  String[] results = new String[2];
		  HttpClient httpClient = new DefaultHttpClient();
		  HttpGet httpGet = new HttpGet((String)params[0]);
		  Header header = new BasicHeader("AUTHORIZATION", "Bearer " +(String)params[1]);
		  Log.d("Auth", header.getName());
		  Log.d("Value", header.getValue());
		  httpGet.setHeader(header);
		  HttpResponse response = null;
		  try {
			response = httpClient.execute(httpGet);
			String status = response.getStatusLine().getStatusCode() + "";
			HttpEntity responseEntity = response.getEntity();
			String responseStr = EntityUtils.toString(responseEntity, "utf-8");
			results[0] = status;
			results[1] = responseStr;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return results;
	}
	
	@Override
	protected void onPostExecute(String[] results){
		Integer status = Integer.parseInt(results[0]);
		if( status== 201){
			
			String responseStr = results[1];
				try {
					
					Log.d("QRCODE", responseStr);
					 final String filePath = getFileRoot(myContext) + File.separator
	                        + "qr_" + System.currentTimeMillis() + ".jpg";
	                boolean success = QRCode.createQRImage(responseStr, 800, 800,filePath);
	                ImageView imageView = (ImageView) activitiy.findViewById(R.id.get_qr_iv);
	                if(success){
	                	imageView.setImageBitmap(BitmapFactory.decodeFile(filePath));
	                }
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	 private String getFileRoot(Context context) {
	        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
	            File external = context.getExternalFilesDir(null);
	            if (external != null) {
	                return external.getAbsolutePath();
	            }
	        }
	        return context.getFilesDir().getAbsolutePath();
	    }

}
