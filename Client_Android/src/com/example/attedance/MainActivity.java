package com.example.attedance;

import java.io.File;

import com.example.qrcode.QRCode;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        final EditText contentET = (EditText) findViewById(R.id.create_qr_content);
        final ImageView imageView = (ImageView) findViewById(R.id.create_qr_iv);
       // final CheckBox addLogoCB = (CheckBox) findViewById(R.id.create_qr_addLogo);
        Button createQrBtn = (Button) findViewById(R.id.create_qr_btn);
 
        createQrBtn.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View v) {
                final String filePath = getFileRoot(MainActivity.this) + File.separator
                        + "qr_" + System.currentTimeMillis() + ".jpg";
                boolean success = QRCode.createQRImage(contentET.getText().toString().trim(), 800, 800,filePath);
                if(success){
                	 imageView.setImageBitmap(BitmapFactory.decodeFile(filePath));
                }
 
            }
        });
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
