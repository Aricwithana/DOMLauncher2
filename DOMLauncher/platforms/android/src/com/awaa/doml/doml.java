package com.awaa.doml;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.preference.PreferenceManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;


public class doml extends CordovaPlugin { 
	
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {      
        if(action.equals("getDMD")){
            File sdcard = Environment.getExternalStorageDirectory();
            File f = new File(sdcard,"/DOMLauncher");
            File[] files = f.listFiles();
            JSONArray jArray = new JSONArray();

            for (File inFile : files) {
                if (inFile.isDirectory()) {		    	
                    String[] folderName = inFile.toString().split("/");			
                    String lastItem = folderName[folderName.length - 1];					    							
                    jArray.put(lastItem);
                }
            }

            callbackContext.success(new JSONObject().put("returnVal", jArray));	
        }		

        if(action.equals("restart")){
            restartApp();    
        }
        
        if(action.equals("setDMD")){
            File sdcard = Environment.getExternalStorageDirectory();     
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.cordova.getActivity());
            String dmdName = args.getJSONObject(0).getString("name");
            File dmd = new File(sdcard+"/DOMLauncher/"+dmdName+"/index.html");
            
            if(dmd.exists()){    
                Editor editor = sharedPrefs.edit();
                editor.putString("active", dmdName);
                if (editor.commit()){
                    this.webView.sendJavascript("window.location = 'file://"+sdcard+"/DOMLauncher/"+dmdName+"/index.html'");   
                }else{
                    callbackContext.success(new JSONObject().put("returnVal", false));	
                }             	
            }
           
        }
		return true;  	
    }
        
	private void restartApp() {			
		cordova.getActivity().finish(); 
		cordova.getActivity().startActivity(new Intent(cordova.getActivity(), cordova.getActivity().getClass()));	
	} 
}