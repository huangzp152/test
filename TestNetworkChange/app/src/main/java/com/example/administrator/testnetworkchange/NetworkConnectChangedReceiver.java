package com.example.administrator.testnetworkchange;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbManager;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/12/15.
 */
public class NetworkConnectChangedReceiver extends BroadcastReceiver {

    private final static String TAG="hzp";

    public NetworkConnectChangedReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        if(WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())){
//            int wifiState=intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,0);
//           // UsbManager.ACTION_USB_DEVICE_ATTACHED;
//        }
        Log.d("checkusb",intent.getAction());
        Toast.makeText(context,"受到广播"+intent.getAction(),Toast.LENGTH_LONG).show();
        if(UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(intent.getAction())){
            Toast.makeText(context,"检测到usb",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public IBinder peekService(Context myContext, Intent service) {
        return super.peekService(myContext, service);
    }
}
