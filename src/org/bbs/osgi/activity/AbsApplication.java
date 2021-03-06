package org.bbs.osgi.activity;

import org.bbs.osgi.activity.ApplicationAgent;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.res.Configuration;

public class AbsApplication extends Application {
	private static final String TAG = AbsApplication.class.getSimpleName();
	protected static boolean LOG = true;
	protected static boolean LOG_LIFECYCLE = false && LOG;
	protected static boolean LOG_MEMORY = true && LOG;
	protected static boolean LOG_CONTENT = true && LOG;
//do NOT edit this file, auto-generated by createTemplage.groovy from StubApplication.java.template

	protected List<ApplicationAgent> mAgents;
	
	public void callStubOnCreate(ApplicationAgent stub){
		stub.onCreate();
		if (LOG_LIFECYCLE) {
			_log(stub, TAG, "call stub onCreate().");
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mAgents = new ArrayList<ApplicationAgent>();
	}

	@Override
//do NOT edit this file, auto-generated by createTemplage.groovy from StubApplication.java.template
	public void onTerminate() {
		super.onTerminate();
		for (ApplicationAgent a : mAgents) {
			a.onTerminate();
			if (LOG_MEMORY) {
				_log(a, TAG, "call stub onTerminate().");
			}
		}
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		for (ApplicationAgent a : mAgents) {
			a.onConfigurationChanged(newConfig);
			if (LOG_CONTENT) {
				_log(a, TAG, "call stub onConfigurationChanged(). newConfig: " + newConfig);
			}
//do NOT edit this file, auto-generated by createTemplage.groovy from StubApplication.java.template
		}
	}
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		for (ApplicationAgent a : mAgents) {
			a.onLowMemory();
			if (LOG_MEMORY) {
				_log(a, TAG, "call stub onLowMemory().");
			}
		}
	}

	@SuppressLint("NewApi")
	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);
//do NOT edit this file, auto-generated by createTemplage.groovy from StubApplication.java.template
		for (ApplicationAgent a : mAgents) {
			a.onTrimMemory(level);
			if (LOG_MEMORY) {
				_log(a, TAG, "call stub onTrimMemory(). level: " + level);
			}
		}
	}	
	
	
	// auxiliary function
	private void _log(ApplicationAgent agent, String tag, String message) {
	   logD(tag, message);
	   logD(tag, "call stub:" + agent);
    }
    protected void logD(String tag, String message) {
	   android.util.Log.d(tag, message);
    }	
//do NOT edit this file, auto-generated by createTemplage.groovy from StubApplication.java.template

}
