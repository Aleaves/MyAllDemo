package com.app.liulongbing.myalldemo.download;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by liulongbing on 16/12/27.
 */

public class DownloadService extends Service{

    public static final String ACTION_START="ACTION_START";
    public static final String ACTION_STOP="ACTION_STOP";
    public static final String ACTION_UPDATE="ACTION_UPDATE";
    public static final int MSG_INIT = 0;

    public static final String DOWNLOAD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
            +"/downloads/";


    private DownloadTask mTask =null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(ACTION_START.equals(intent.getAction())){

            FileInfo fileInfo = (FileInfo) intent.getSerializableExtra("fileInfo");

            Log.i("=========",ACTION_START+"==="+fileInfo.getFileName());

            new InitThread(fileInfo).start();

        }else if(ACTION_STOP.equals(intent.getAction())){

            FileInfo fileInfo = (FileInfo) intent.getSerializableExtra("fileInfo");

            Log.i("=========",ACTION_STOP+"==="+fileInfo.getFileName());


            if(mTask!=null){

                mTask.isPause = true ;

            }
        }


        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_INIT:
                    FileInfo fileInfo = (FileInfo) msg.obj;

                    Log.i("========","init"+fileInfo.getLength());

                    mTask = new DownloadTask(DownloadService.this,fileInfo);

                    mTask.download();

                    break;
            }
        }
    };


    class InitThread extends Thread{

        private FileInfo fileInfo =null;

        public InitThread(FileInfo fileInfo){
             this.fileInfo = fileInfo;
        }

        @Override
        public void run() {
            super.run();

            RandomAccessFile raf = null;
            HttpURLConnection conn = null;

            try {
                URL url = new URL(fileInfo.getUrl());
                conn= (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(3000);
                conn.setRequestMethod("GET");
                int length = -1;
                length =conn.getContentLength();
                if(length<=0){
                    return;
                }

                File dir = new File(DOWNLOAD_PATH);
                if(!dir.exists()){
                    dir.mkdir();
                }

                File file = new File(dir,fileInfo.getFileName());

                raf= new RandomAccessFile(file,"rwd");
                raf.setLength(length);
                fileInfo.setLength(length);

                mHandler.obtainMessage(MSG_INIT,fileInfo).sendToTarget();



            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    conn.disconnect();
                    raf.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }

}
