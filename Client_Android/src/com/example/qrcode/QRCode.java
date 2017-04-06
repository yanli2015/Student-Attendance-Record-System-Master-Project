package com.example.qrcode;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

import com.google.zxing.*;
import com.google.zxing.common.*;
import com.google.zxing.qrcode.*;

import android.graphics.Bitmap;

public class QRCode {
	
		
	
	    public static boolean createQRImage(String url, int  QR_WIDTH, int  QR_HEIGHT, String filePath){
	        try{
	          
	            if (url == null || "".equals(url) || url.length() < 1)
	            {
	                return false;
	            }
	            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
	            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
	            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
	            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
	            for (int y = 0; y < QR_HEIGHT; y++){
	                for (int x = 0; x < QR_WIDTH; x++)
	                {
	                    if (bitMatrix.get(x, y))
	                    {
	                        pixels[y * QR_WIDTH + x] = 0xff000000;
	                    }
	                    else
	                    {
	                        pixels[y * QR_WIDTH + x] = 0xffffffff;
	                    }
	                }
	            }
	            Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
	            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
	         //   sweepIV.setImageBitmap(bitmap);
	            return bitmap != null && bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(filePath));
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        return false;
	    }

}


