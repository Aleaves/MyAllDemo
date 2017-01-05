package com.app.liulongbing.myalldemo.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.liulongbing.myalldemo.MainActivity;
import com.app.liulongbing.myalldemo.R;

/**
 * Created by liulongbing on 16/12/27.
 */

public class DownLoadActivity extends AppCompatActivity{

    private TextView mTvFileName;
    private ProgressBar mProgress;
    private Button mBtStart,mBtStop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        mTvFileName = (TextView)findViewById(R.id.tv_fileName);
        mProgress = (ProgressBar)findViewById(R.id.progress);
        mBtStart = (Button)findViewById(R.id.bt_start);
        mBtStop = (Button)findViewById(R.id.bt_stop);
        mProgress.setMax(100);

        final FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName("邻家女孩.jpg");

        fileInfo.setUrl("http://ac-c6scxa78.clouddn.com/eb9ff11247aa60784907.jpg");
        fileInfo.setId(0);
        fileInfo.setLength(0);
        fileInfo.setFinished(0);

        mBtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DownLoadActivity.this,DownloadService.class);
                intent.setAction(DownloadService.ACTION_START);
                intent.putExtra("fileInfo",fileInfo);
                startService(intent);

            }
        });

        mBtStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DownLoadActivity.this,DownloadService.class);
                intent.setAction(DownloadService.ACTION_STOP);
                intent.putExtra("fileInfo",fileInfo);
                startService(intent);
            }
        });

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadService.ACTION_UPDATE);
        registerReceiver(MyReceiver,intentFilter);

    }

    BroadcastReceiver MyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(DownloadService.ACTION_UPDATE.equals(intent.getAction())){
                int finished = intent.getIntExtra("finished",0);

                Log.i("=====",finished+"");

                Log.i("========","shoudao");

                mProgress.setProgress(finished);

            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(MyReceiver);
    }

}
