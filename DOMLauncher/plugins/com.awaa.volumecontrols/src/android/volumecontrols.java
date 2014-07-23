package com.awaa.volumecontrols;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import android.content.Context;
import android.media.AudioManager;

public class volumecontrols extends CordovaPlugin { 
	
	 @Override
	    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		
        AudioManager audioManager = (AudioManager)this.cordova.getActivity().getSystemService(Context.AUDIO_SERVICE);
        String stream = args.getJSONObject(0).getString("stream");
        String flag = args.getJSONObject(0).getString("flag");
        String toast = args.getJSONObject(0).getString("toast");
        
		 //Ringer Check
		 if(action.equals("check")){
            if(stream.equals("ringer")){
                if(flag.equals("percentage")){
                    int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
                    float curPercent = (curVolume* 100) / audioManager.getStreamMaxVolume(AudioManager.STREAM_RING) ;
                    callbackContext.success(new JSONObject().put("returnVal", curPercent));
                }else if(flag.equals("max")){	
                    int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
                    callbackContext.success(new JSONObject().put("returnVal", maxVolume));
                }else if(flag.equals("mode")){
                    //Ringer Mode Check 0 - Silent/ 1 - Vibrate/ 2 - Normal
                    int mode = audioManager.getRingerMode();
                    String modeName = null;
                    if(mode == 0){
                        modeName = "silent";
                    }else if(mode == 1){
                        modeName = "vibrate";
                    }else if(mode == 2){
                        modeName = "normal";
                    }
                    callbackContext.success(new JSONObject().put("returnVal", modeName));           
                }else{
                    int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);		
                    callbackContext.success(new JSONObject().put("returnVal", curVolume));						
                }           
            }else if(stream.equals("media")){
                if(flag.equals("percentage")){
                    int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    float curPercent = (curVolume* 100) / audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) ;
                    callbackContext.success(new JSONObject().put("returnVal", curPercent));
                }else if(flag.equals("max")){	
                    int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                    callbackContext.success(new JSONObject().put("returnVal", maxVolume));
                }else{
                    int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);		
                    callbackContext.success(new JSONObject().put("returnVal", curVolume));						
                }	           
            }
		 }
		 
         if(action.equals("up")){
            if(stream.equals("ringer")){
                int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
                if(toast.equals("true")){																	
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    audioManager.setStreamVolume(AudioManager.STREAM_RING, curVolume + 1, AudioManager.FLAG_SHOW_UI);
                    int newVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
                    callbackContext.success(new JSONObject().put("returnVal", newVolume));
                }else{
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    audioManager.setStreamVolume(AudioManager.STREAM_RING, curVolume + 1, 0);
                    int newVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
                    callbackContext.success(new JSONObject().put("returnVal", newVolume));
                }                       
            }
            
            if(stream.equals("media")){                
                int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                if(toast.equals("true")){																	
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, curVolume + 1, AudioManager.FLAG_SHOW_UI);
                    int newVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    callbackContext.success(new JSONObject().put("returnVal", newVolume));
                }else{
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, curVolume + 1, 0);
                    int newVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    callbackContext.success(new JSONObject().put("returnVal", newVolume));
                }
            }
         }
	 		 
 
        if(action.equals("percentage")){
            int percentVal = Integer.parseInt(args.getJSONObject(0).getString("value"));
            
            if(stream.equals("ringer")){
                int mediaVal = (percentVal * audioManager.getStreamMaxVolume(AudioManager.STREAM_RING)) / 100;
                if(toast.equals("true")){
                    audioManager.setStreamVolume(AudioManager.STREAM_RING, mediaVal, AudioManager.FLAG_SHOW_UI);
                    callbackContext.success(new JSONObject().put("returnVal", percentVal));
                }else{
                    audioManager.setStreamVolume(AudioManager.STREAM_RING, mediaVal, 0);
                    callbackContext.success(new JSONObject().put("returnVal", percentVal));
                }            
            }
            
            if(stream.equals("media")){      
                int mediaVal = (percentVal * audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)) / 100;
                if(toast.equals("true")){
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mediaVal, AudioManager.FLAG_SHOW_UI);
                    callbackContext.success(new JSONObject().put("returnVal", percentVal));
                }else{
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mediaVal, 0);
                    callbackContext.success(new JSONObject().put("returnVal", percentVal));
                }             
            }    
        }
 
        if(action.equals("down")){
            if(stream.equals("ringer")){
                int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
                if(toast.equals("true")){																	
                    if(curVolume == 0){	
                        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                    }else{	
                        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                        audioManager.setStreamVolume(AudioManager.STREAM_RING, curVolume - 1, AudioManager.FLAG_SHOW_UI);
                    }
                    int newVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
                    callbackContext.success(new JSONObject().put("returnVal", newVolume));
                }else{
                    if(curVolume == 0){	
                        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                    }else{	
                        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                        audioManager.setStreamVolume(AudioManager.STREAM_RING, curVolume - 1, 0);
                    }
                    int newVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
                    callbackContext.success(new JSONObject().put("returnVal", newVolume));
                }
            }

            if(stream.equals("media")){
                int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                if(toast.equals("true")){																	
                    if(curVolume == 0){	
                    }else{
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, curVolume - 1, AudioManager.FLAG_SHOW_UI);
                    }
                    int newVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    callbackContext.success(new JSONObject().put("returnVal", newVolume));
                }else{
                    if(curVolume == 0){	
                    }else{
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, curVolume - 1, 0);
                    }
                    int newVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    callbackContext.success(new JSONObject().put("returnVal", newVolume));
                }
            }
        }
 
 	 
        
		 if(action.equals("mute")){									
			if(toast.equals("true")){																	
				audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, AudioManager.FLAG_SHOW_UI);
                callbackContext.success(new JSONObject().put("returnVal", 0));
			}else{
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
                callbackContext.success(new JSONObject().put("returnVal", 0));
			}
		 }	
		         
        
		 //Ringer Modes
		 if(action.equals("vibrate")){									
             audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
			int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
			callbackContext.success(new JSONObject().put("returnVal", curVolume));						
		 
		 }			 
		 
		 if(action.equals("silent")){									
			 audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
			callbackContext.success(new JSONObject().put("returnVal", curVolume));						
		 
		 }	
		 
		 if(action.equals("normal")){									
			 if(flag.equals("percentage")){
				 	audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);			 		
				 	int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
				 	float curPercent = (curVolume* 100) / audioManager.getStreamMaxVolume(AudioManager.STREAM_RING) ;
					callbackContext.success(new JSONObject().put("returnVal", curPercent));
				}else{
					audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);									
					int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
					callbackContext.success(new JSONObject().put("returnVal", curVolume));						
				}			
			
		 
		 }
		 	 
		return true;
	}  
}