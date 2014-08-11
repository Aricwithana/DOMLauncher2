package com.awaa.wificontrols;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.net.wifi.WifiManager;

public class wificontrols extends CordovaPlugin { 


BroadcastReceiver wifireceiver;
String callback;
	public wificontrols() {
	    this.wifireceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				
				if (intent.getAction().equals(WifiManager.RSSI_CHANGED_ACTION)) {
					updateSignalStrength(intent.getIntExtra(WifiManager.EXTRA_NEW_RSSI, -1));
				}
			}
		};
	}
	 
    private void updateSignalStrength(int strengthDbm) {
    	this.webView.sendJavascript(callback+"(" + strengthDbm + ")");   	
    }
	
    @Override
    public void onPause(boolean multitasking)
    {
        stopListen();
    }
   
    @Override
    public void onResume(boolean multitasking)
    {
        startListen();
    }
   
    @Override
    public void onDestroy()
    {
    	try {this.cordova.getActivity().unregisterReceiver(wifireceiver);} catch(IllegalArgumentException e) {}
    }
	
    private void startListen()
    {
		IntentFilter rssiFilter = new IntentFilter();
		rssiFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
		this.cordova.getActivity().registerReceiver(wifireceiver, rssiFilter);
    }


	private void stopListen()
    {
		try {this.cordova.getActivity().unregisterReceiver(wifireceiver);} catch(IllegalArgumentException e) {}
		
    }	
	
	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		
		WifiManager wifiManager = (WifiManager)this.cordova.getActivity().getSystemService(Context.WIFI_SERVICE);	
		
			if(action.equals("check")){				
				if (wifiManager.isWifiEnabled()) {	
					callback = args.getJSONObject(0).getString("callback");
					startListen();
					callbackContext.success(new JSONObject().put("returnVal", "enabled"));
				}else{
					callbackContext.success(new JSONObject().put("returnVal", "disabled"));
				}												
			}
			
			if(action.equals("enable")){
				callback = args.getJSONObject(0).getString("callback");
				startListen();
				wifiManager.setWifiEnabled(true);	
				callbackContext.success(new JSONObject().put("returnVal", "enabled"));
				
			}
				
			if(action.equals("disable")){
				stopListen();
				wifiManager.setWifiEnabled(false);									
				callbackContext.success(new JSONObject().put("returnVal", "disabled"));
			}
					
			if(action.equals("toggle")){
				if (wifiManager.isWifiEnabled()) {				   
					stopListen();
					wifiManager.setWifiEnabled(false);									
					callbackContext.success(new JSONObject().put("returnVal", "disabled"));				
				}else{
					callback = args.getJSONObject(0).getString("callback");
					startListen();
					wifiManager.setWifiEnabled(true);
					callbackContext.success(new JSONObject().put("returnVal", "enabled"));
				}							
			}
            
			if(action.equals("stop")){
				if (wifiManager.isWifiEnabled()) {				   
					stopListen();						
					callbackContext.success(new JSONObject().put("returnVal", "stopped"));				
				}							
			}            		
		return true;		

    }

}