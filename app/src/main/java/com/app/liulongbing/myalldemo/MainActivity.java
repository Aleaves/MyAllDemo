package com.app.liulongbing.myalldemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.app.liulongbing.myalldemo.async.AsyncActivity;
import com.app.liulongbing.myalldemo.async.AsyncProgressActivity;
import com.app.liulongbing.myalldemo.download.DownLoadActivity;
import com.app.liulongbing.myalldemo.listAdapter.ListAdapterActivity;
import com.app.liulongbing.myalldemo.one.OneActivity;
import com.app.liulongbing.myalldemo.two.TwoActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OneActivity.class));
            }
        });


        findViewById(R.id.bt_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TwoActivity.class));
            }
        });

        findViewById(R.id.bt_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListAdapterActivity.class));
            }
        });

        findViewById(R.id.bt_four).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AsyncActivity.class));
            }
        });

        findViewById(R.id.bt_five).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, AsyncProgressActivity.class));

            }
        });

        findViewById(R.id.bt_six).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DownLoadActivity.class));
            }
        });

    }

}
