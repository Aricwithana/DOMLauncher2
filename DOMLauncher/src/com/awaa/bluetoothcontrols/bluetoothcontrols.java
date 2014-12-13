package com.awaa.bluetoothcontrols;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import android.bluetooth.BluetoothAdapter;


public class bluetoothcontrols extends CordovaPlugin { 
	
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	
		if(action.equals("enable")){				
			if (mBluetoothAdapter == null) {
				callbackContext.success(new JSONObject().put("returnVal", null));   
			} else {
				mBluetoothAdapter.enable();
		    	callbackContext.success(new JSONObject().put("returnVal", "enabled"));
			}
		}
		
		if(action.equals("disable")){
			if (mBluetoothAdapter == null) {
				callbackContext.success(new JSONObject().put("returnVal", null));   
			} else { 
				mBluetoothAdapter.disable();
				callbackContext.success(new JSONObject().put("returnVal", "disabled"));
			}
		}
		
		if(action.equals("toggle")){			
			if (mBluetoothAdapter == null) {
				callbackContext.success(new JSONObject().put("returnVal", null));   
			} else {
				if(mBluetoothAdapter.isEnabled())
			    {
			      mBluetoothAdapter.disable();
			      callbackContext.success(new JSONObject().put("returnVal", "disabled"));
			    }else{
			      mBluetoothAdapter.enable();
			      callbackContext.success(new JSONObject().put("returnVal", "enabled"));
			    }
			}
		}
		
		if(action.equals("check")){	
			if (mBluetoothAdapter == null) {
			  callbackContext.success(new JSONObject().put("returnVal", null)); 
			} else {
			    if (mBluetoothAdapter.isEnabled()) {
			    	callbackContext.success(new JSONObject().put("returnVal", "enabled"));
			    }else{
			    	callbackContext.success(new JSONObject().put("returnVal", "disabled"));	
			    }
			}
		}

		
		return true;

        
	}  
}