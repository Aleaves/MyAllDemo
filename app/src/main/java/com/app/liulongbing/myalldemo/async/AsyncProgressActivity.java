package com.app.liulongbing.myalldemo.async;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.app.liulongbing.myalldemo.R;

/**
 * Created by liulongbing on 16/12/27.
 */

public class AsyncProgressActivity extends AppCompatActivity{

    ProgressBar mProgressBar;
    ProgressAsyncTask mTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_progress);

        mProgressBar = (ProgressBar)findViewById(R.id.progressbar);

        mTask = new ProgressAsyncTask();
        mTask.execute();


    }

    @Override
    protected void onPause() {
        super.onPause();

        if(mTask!=null&&mTask.getStatus()==AsyncTask.Status.RUNNING){
            mTask.cancel(true);
        }
    }

    class ProgressAsyncTask extends AsyncTask<Void,Integer,Void>{


        @Override
        protected Void doInBackground(Void... voids) {

            if(isCancelled()){

            }

            for (int i=0;i<100;i++){
                publishProgress(i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            mProgressBar.setProgress(values[0]);
        }
    }


}
