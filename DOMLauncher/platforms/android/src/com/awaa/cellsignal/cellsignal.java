package com.awaa.cellsignal;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;

public class cellsignal extends CordovaPlugin { 
	      
    PhoneStateListener phoneStateListener;
    String callback;

    public cellsignal() {
            this.phoneStateListener = new PhoneStateListener() {
                    @Override 
                    public void onSignalStrengthsChanged(SignalStrength signalStrength)
                    {
                            super.onSignalStrengthsChanged(signalStrength);

                            int strengthDbm = -1;
                            if (signalStrength.isGsm()){

                                    strengthDbm = -140 + 2 * signalStrength.getGsmSignalStrength();
                            } else {
                                    strengthDbm = signalStrength.getCdmaDbm();
                                    if (strengthDbm == -1){
                                            strengthDbm = signalStrength.getEvdoDbm();
                            }}
                            updateSignalStrength(strengthDbm);
                    }
            };
    }

    private void updateSignalStrength(int strengthDbm) {
        this.webView.sendJavascript(callback+"(" + strengthDbm + ")");  
    }

    @Override
    public void onPause(boolean multitasking){
        stopListen();
    }

    @Override
    public void onResume(boolean multitasking){
        startListen();
    }

    @Override
    public void onDestroy(){
    	stopListen();
    }

    private void startListen(){ 
    	TelephonyManager telephonyManager = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
    }
    private void stopListen(){ 
    	TelephonyManager telephonyManager = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_NONE);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if (action.equals("enable")) {
            callback = args.getJSONObject(0).getString("callback");
            startListen();
            callbackContext.success(new JSONObject().put("returnVal", "enabled"));
        } 
        if(action.equals("disable")) {
           stopListen();
           callbackContext.success(new JSONObject().put("returnVal", "disabled"));
        }

        return true;
    }
}