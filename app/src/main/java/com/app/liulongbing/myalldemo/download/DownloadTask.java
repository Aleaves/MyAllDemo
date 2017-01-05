package com.app.liulongbing.myalldemo.download;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by liulongbing on 16/12/28.
 */

public class DownloadTask {

    private Context context;

    private FileInfo fileInfo;

    private ThreadDao mDao;

    private int mFinished =0;

    public  boolean isPause = false;

    public DownloadTask(Context context, FileInfo fileInfo) {
        this.context = context;
        this.fileInfo = fileInfo;
        mDao = new ThreadDaoImp(context);
    }


    public void download(){

        List<ThreadInfo> threadInfos = mDao.getThreads(fileInfo.getUrl());

        ThreadInfo threadInfo = null;
        if(threadInfos.size()==0){

            threadInfo = new ThreadInfo(0,0,fileInfo.getUrl(),fileInfo.getLength(),0);

        }else{

            threadInfo = threadInfos.get(0);

        }

        new DownloadThread(threadInfo).start();

    }

    class DownloadThread extends Thread{
        private ThreadInfo mThreadInfo;
        public DownloadThread(ThreadInfo mThreadInfo){
            this.mThreadInfo = mThreadInfo;
        }

        @Override
        public void run() {

            if(!mDao.isExists(mThreadInfo.getUrl(),mThreadInfo.getId())){
                mDao.insertThread(mThreadInfo);
            }

            HttpURLConnection conn =null;
            RandomAccessFile raf=null;
            InputStream is=null;

            int start = mThreadInfo.getStart()+mThreadInfo.getFinished();


            try {
                URL url = new URL(mThreadInfo.getUrl());
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(3000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Range","bytes="+start+"-"+mThreadInfo.getEnd());
                File file = new File(DownloadService.DOWNLOAD_PATH,fileInfo.getFileName());
                raf= new RandomAccessFile(file,"rwd");
                raf.seek(start);

                Intent intent = new Intent(DownloadService.ACTION_UPDATE);
                mFinished += mThreadInfo.getFinished();

                Log.i("===========",conn.getResponseCode()+"");

                if(true){

                    is = conn.getInputStream();
                    byte[] buff = new byte[1024];
                    int len =-1;
                    long time = System.currentTimeMillis();

                    while((len=is.read(buff))!=-1){

                        raf.write(buff,0,len);


                        mFinished +=len;

                        if(System.currentTimeMillis() - time >500) {

                            time = System.currentTimeMillis();

                            Log.i("=========","send broadcast"+mFinished);

                            intent.putExtra("finished", (mFinished * 100)/ fileInfo.getLength());

                            context.sendBroadcast(intent);

                        }

                        if(isPause){
                            mDao.updateThread(mThreadInfo.getUrl(),mThreadInfo.getId(),mFinished);
                            return;
                        }

                    }
                    //if(mFinished ==fileInfo.getLength()) {
                        mDao.deleteThread(mThreadInfo.getUrl(), mThreadInfo.getId());
                    //}

                }

            } catch (Exception e) {

                e.printStackTrace();

            }finally {

                try {

                    conn.disconnect();

                    raf.close();

                    is.close();

                }catch (Exception e){

                }


            }


        }
    }


}
