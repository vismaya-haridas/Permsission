package com.battery;


import com.facebook.react.ReactActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.content.Context;
import android.os.PowerManager;
import android.provider.Settings;
import androidx.annotation.RequiresPermission;
import androidx.core.content.ContextCompat;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

public class MainActivity extends ReactActivity {
  public static final int ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS = 2323;
  public static final int PERMISSION_REQUEST_CODE =  200;

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "battery";
  }

   @Override
  public void onStart() {
    super.onStart();
    requestbatteryPermissioncall();
  }

public void requestForPermission() {
       ActivityCompat.requestPermissions(MainActivity.this, new String[]    {Manifest.permission.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS}, PERMISSION_REQUEST_CODE);
}
 private void requestbatteryPermissioncall() {
        //      Intent intent = new Intent();
        // String packageName = getPackageName();
        // PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
        // if (pm.isIgnoringBatteryOptimizations(packageName))
        //     intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
        // else {
        //     intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
        //     intent.setData(Uri.parse("package:" + packageName));
        // }
        //  startActivity(intent);
             if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent();
            String packageName = getPackageName();
            PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + packageName));
                startActivity(intent);
            }
        }
    }


 @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Granted

                } else {



                  //  @Override
                  //               public void onClick(DialogInterface dialog, int which) {
                  //                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                  //                       requestPermissions(new String[]{Manifest.permission.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS},
                  //                               PERMISSION_REQUEST_CODE);
                  //                   }
                  //               }
                    //  showMessageOK("You need to allow access to both the permissions",
                        // new DialogInterface.OnClickListener() {
                          // @Override
                          // public void onClick(DialogInterface dialog, int which) {
                              //  Intent intent = new Intent();
        // String packageName = getPackageName();
        // PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
                              //  intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            // intent.setData(Uri.parse("package:" + packageName));
                            // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                              // requestPermissions(new String[]{ ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS},
                                      // PERMISSION_REQUEST_CODE)
                            // }
                          // }
                        // });
                // return;
                }
            }
        }
    }


 private void showMessageOK(String message, DialogInterface.OnClickListener okListener) {
    new AlertDialog.Builder(MainActivity.this)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .create()
            .show();
  }
}
