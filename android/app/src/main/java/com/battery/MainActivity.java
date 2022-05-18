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
import android.Manifest;
import android.widget.Toast;
public class MainActivity extends ReactActivity {
    public static final int ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS = 2323;
    // public static final int PERMISSION_REQUEST_CODE =  200;
// 

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

    // public void requestForPermission() {
//        ActivityCompat.requestPermissions(MainActivity.this, new String[]    {android.Manifest.permission.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS}, PERMISSION_REQUEST_CODE);
// }
    private void requestbatteryPermissioncall() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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


    int count = 0;

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //do your task here

                } else {
                    permission_denied();
                }
                break;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void permission_denied() {
        // permission was not granted
        //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
        // shouldShowRequestPermissionRationale will return true
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)) {
            showDialogOK("Allow access to proceed.",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    //user enables permisssion do your task..
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    // proceed with logic by disabling the related features or quit the app.
                                        finish();

                                    // if (count == 5) {
                                    //     finish();
                                    //     //finish activity
                                    // } else {
                                    //     ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS}, REQUEST_INTERNET);
                                    //     ++count;
                                    // }
                                    break;
                            }
                        }
                    });
        } //permission is denied (and never ask again is  checked)
        //shouldShowRequestPermissionRationale will return false
        else {
            Toast.makeText(getApplicationContext(), "Go to settings and enable record audio permissions", Toast.LENGTH_LONG).show();
        }
    }


    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity
                .this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }
}
//  @Override
//     public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
//         switch (requestCode) {
//             case ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS: {
//                 if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                     //Granted
//                 } else {
//                      showMessageOK("You need to allow access to both the permissions",
//                         new DialogInterface.OnClickListener() {
//                           @Override
//                           public void onClick(DialogInterface dialog, int which) {
//                                Intent intent = new Intent();
//                                String packageName = getPackageName();
//                                PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
//                                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
//                                intent.setData(Uri.parse("package:" + packageName));
//                             // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                             //   requestPermissions(new String[]{ ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS},
//                             //           PERMISSION_REQUEST_CODE)
//                             // }
//                           }
//                         });
//                 return;
//                 }
//             }
//         }
//     }


//  private void showMessageOK(String message, DialogInterface.OnClickListener okListener) {
//     new AlertDialog.Builder(MainActivity.this)
//             .setMessage(message)
//             .setPositiveButton("OK", okListener)
//             .create()
//             .show();
//   }
// }
