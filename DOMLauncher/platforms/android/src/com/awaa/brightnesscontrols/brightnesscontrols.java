package com.awaa.brightnesscontrols;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.provider.Settings.SettingNotFoundException;

public class brightnesscontrols extends CordovaPlugin {
	
	@Override
    public boolean execute(String action, JSONArray args,  CallbackContext callbackContext) throws JSONException {
		
		if(action.equals("value")){
			int value = args.getJSONObject(0).getInt("value");
			if(value > 0 && value <= 255){
				android.provider.Settings.System.putInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, 0); 
				android.provider.Settings.System.putInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS, value);
				callbackContext.success(new JSONObject().put("returnVal", value));
			}else if(value < 1){
				android.provider.Settings.System.putInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, 0); 
				android.provider.Settings.System.putInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS, 1);
				callbackContext.success(new JSONObject().put("returnVal", value));			
				
			}else if(value > 255){
				android.provider.Settings.System.putInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, 0); 
				android.provider.Settings.System.putInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS, 255);
				callbackContext.success(new JSONObject().put("returnVal", value));	
			}
		}
		
		if(action.equals("up")){	
			try {
				int value = args.getJSONObject(0).getInt("step");
				int curValue = android.provider.Settings.System.getInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS);
				int newValue = curValue + value;
				if(newValue <= 255){
					android.provider.Settings.System.putInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, 0);
					android.provider.Settings.System.putInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS, newValue);
					callbackContext.success(new JSONObject().put("returnVal", newValue));
				}else{
					android.provider.Settings.System.putInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, 0);
					android.provider.Settings.System.putInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS, 255);
					callbackContext.success(new JSONObject().put("returnVal", 255));	
				}		
			} catch (SettingNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(action.equals("down")){	
			try {
				int value = args.getJSONObject(0).getInt("step");
				int curValue = android.provider.Settings.System.getInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS);
				int newValue = curValue - value;
				if(newValue >= 1){
					android.provider.Settings.System.putInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, 0);
					android.provider.Settings.System.putInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS, newValue);
					callbackContext.success(new JSONObject().put("returnVal", newValue));
				}else{
					android.provider.Settings.System.putInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, 0);
					android.provider.Settings.System.putInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS, 1);
					callbackContext.success(new JSONObject().put("returnVal", 1));	
				}		
			} catch (SettingNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(action.equals("enable")){	
			android.provider.Settings.System.putInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, 1);
			callbackContext.success(new JSONObject().put("returnVal", "enabled"));
		}
		
		if(action.equals("disable")){	
			android.provider.Settings.System.putInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, 0);
			callbackContext.success(new JSONObject().put("returnVal", "disabled"));
		}
		
		if(action.equals("toggle")){	
			
			try {
				int getMode = android.provider.Settings.System.getInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE);
				if(getMode == 1){					
					android.provider.Settings.System.putInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, 0);				
					callbackContext.success(new JSONObject().put("returnVal", "disabled"));
				}

				if(getMode == 0){									
					android.provider.Settings.System.putInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, 1);

					callbackContext.success(new JSONObject().put("returnVal", "enabled"));
				}		
			} catch (SettingNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			

		}
			
		if(action.equals("check")){	
			
			try {
				String flag = args.getJSONObject(0).getString("flag");
				if(flag.equals("mode")){
					int getMode = android.provider.Settings.System.getInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE);
					if(getMode == 0){									
						callbackContext.success(new JSONObject().put("returnVal", "disabled"));
					}

					if(getMode == 1){									
						callbackContext.success(new JSONObject().put("returnVal", "enabled"));
					}									
				}
				
				if(flag.equals("value")){
					int getBright = android.provider.Settings.System.getInt(cordova.getActivity().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS);
					callbackContext.success(new JSONObject().put("returnVal", getBright));	
				}
	
			} catch (SettingNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			

		}			
		return true;
    }
}