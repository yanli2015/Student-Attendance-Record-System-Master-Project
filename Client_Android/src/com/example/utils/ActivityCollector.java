package com.example.utils;

import java.util.*;

import android.app.Activity;

public class ActivityCollector {
	
	public static List<Activity> activities = new ArrayList<>();
	
	public static void addActivity(Activity activity){
		activities.add(activity);
	}
	
	public static void removeActivity(Activity activity){
		activities.remove(activity);
	}
	
	public static void logout(){
		finishAll();
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}
	
	public static void finishAll(){
		for(Activity activity : activities){
			if(!activity.isFinishing()){
				activity.finish();
			}
		}
	}

}
