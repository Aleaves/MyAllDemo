package com.app.liulongbing.myalldemo.two;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.app.liulongbing.myalldemo.R;

public class TwoActivity extends BaseTwoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCallPermission();
            }
        });



    }

    private void checkCallPermission(){

        if(!checkPermission(Manifest.permission.CALL_PHONE)){
            requestPermission(1,Manifest.permission.CALL_PHONE);
        }else{
            callPhone();
        }

    }


    @Override
    protected void callPhone() {
        //super.callPhone();
        Log.i("==========","打电话");
    }


}
