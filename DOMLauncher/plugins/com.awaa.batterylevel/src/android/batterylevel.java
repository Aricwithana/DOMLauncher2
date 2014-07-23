package com.awaa.batterylevel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import android.content.Intent;
import android.content.IntentFilter;

import android.os.BatteryManager;

public class batterylevel extends CordovaPlugin { 
	
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {      
        Intent batteryIntent = this.cordova.getActivity().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
	    int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
	    int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

		callbackContext.success(new JSONObject().put("returnVal", (((float)level / (float)scale) * 100.0f)));
        return true;
    }

}