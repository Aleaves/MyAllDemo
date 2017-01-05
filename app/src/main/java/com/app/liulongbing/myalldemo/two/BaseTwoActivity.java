package com.app.liulongbing.myalldemo.two;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by liulongbing on 16/12/4.
 */

public class BaseTwoActivity extends AppCompatActivity{

    protected boolean checkPermission(String... permissions){

        for (String permission : permissions){
            if(ContextCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;

    }

    protected void requestPermission(int requestCode,String... permissions){

        ActivityCompat.requestPermissions(this,permissions,requestCode);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                    callPhone();
                }
                break;
        }
    }


    protected void callPhone(){
        Log.i("========","da");
    }


}
