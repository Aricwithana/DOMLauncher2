/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.awaa.domlauncher;

import android.os.Bundle;
import org.apache.cordova.*;

import java.io.File;
import android.os.Environment;
import android.util.Log;
import android.webkit.WebSettings;

import org.json.JSONException;
import org.json.JSONStringer;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class DOMLauncher extends CordovaActivity{ 

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        super.init();
        // Set by <content src="index.html" /> in config.xml
        super.clearCache();      
        super.appView.getSettings().setAppCacheEnabled(false); 
        super.appView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        
		File sdcard = Environment.getExternalStorageDirectory();
        File appDir = new File(sdcard+"/DOMLauncher");
        
        if(sdcard.exists()){
            if(!appDir.exists()){
                appDir.mkdirs();
                super.loadUrl(Config.getStartUrl());
            }else{             	     
                String dmdSetting = getActive();
                String dmdName = dmdSetting.replace("\"", "");
                File dmdLocation = new File(sdcard,"/DOMLauncher/"+dmdName+"/index.html");
                Log.d(TAG, "Activity:" +sdcard+ "/DOMLauncher/"+dmdName+"/index.html");
                if(dmdLocation.exists()){  
                    super.loadUrl("file:///" + dmdLocation.getAbsolutePath()); 
                }else{
                    super.loadUrl(Config.getStartUrl()); 
                }	
            }
        }else{
            super.loadUrl(Config.getStartUrl()); 
        }
    }
    private String getActive() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (sharedPrefs.contains("active")) {
            Object obj = sharedPrefs.getAll().get("active");
            String fakeArray = null;
            try {
                fakeArray = new JSONStringer().array().value((String)obj).endArray().toString();
                
                return fakeArray.substring(1, fakeArray.length()-1);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "therewasanerror";
            }
        }else{
            
            return "NoDMDINSTALLED";
        }
    }
}

