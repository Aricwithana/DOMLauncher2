package com.awaa.mobiledata;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import android.content.Context;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;

public class mobiledata extends CordovaPlugin { 
	
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        try {
			
			Context ctx = this.cordova.getActivity();
			
			final ConnectivityManager conman = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
			final Class<?> conmanClass = Class.forName(conman.getClass().getName());
			final Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
			iConnectivityManagerField.setAccessible(true);
			final Object iConnectivityManager = iConnectivityManagerField.get(conman);
			final Class<?> iConnectivityManagerClass = Class.forName(iConnectivityManager.getClass().getName());
			
			final Method setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
			setMobileDataEnabledMethod.setAccessible(true);
						
			TelephonyManager telephonyManager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);

            if (action.equals("check")) {
                    
                if(telephonyManager.getDataState() == TelephonyManager.DATA_CONNECTED){
                	callbackContext.success(new JSONObject().put("returnVal", "enabled"));
    			}

                if(telephonyManager.getDataState() == TelephonyManager.DATA_DISCONNECTED){
                	callbackContext.success(new JSONObject().put("returnVal", "disabled"));
    			}
                
                if(telephonyManager.getDataState() == TelephonyManager.DATA_SUSPENDED){
                	callbackContext.success(new JSONObject().put("returnVal", "enabled"));
    			}
            } 
            
            if(action.equals("toggle")){         
            	if(telephonyManager.getDataState() == TelephonyManager.DATA_CONNECTED){			
            		setMobileDataEnabledMethod.invoke(iConnectivityManager, false);
            		callbackContext.success(new JSONObject().put("returnVal", "disabled"));
            	}           
	
	            if(telephonyManager.getDataState() == TelephonyManager.DATA_DISCONNECTED){
					setMobileDataEnabledMethod.invoke(iConnectivityManager, true);
					callbackContext.success(new JSONObject().put("returnVal", "enabled"));
				}			
            }
            
            if(action.equals("enable")){
				setMobileDataEnabledMethod.invoke(iConnectivityManager, true);
				callbackContext.success(new JSONObject().put("returnVal", "enabled"));
            }		
			
			
            if(action.equals("disable")){
 				setMobileDataEnabledMethod.invoke(iConnectivityManager, false);
 				callbackContext.success(new JSONObject().put("returnVal", "disabled"));
             }		
 						
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return true;	

        
	}  
}