package com.app.liulongbing.myalldemo.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.app.liulongbing.myalldemo.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by liulongbing on 16/12/27.
 */

public class AsyncActivity extends AppCompatActivity{

    private ImageView image;

    private ProgressBar progressBar;

    public static final String Url ="http://d.hiphotos.baidu.com/image/h%3D200/sign=b85ca2d48344ebf87271633fe9f8d736/2e2eb9389b504fc2065e2bd2e1dde71191ef6de0.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);

        image = (ImageView)findViewById(R.id.image);

        progressBar =(ProgressBar)findViewById(R.id.progressbar);

        MyAsyncTask task = new MyAsyncTask();
        task.execute(Url);

    }

    class MyAsyncTask extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {

            String url =strings[0];
            Bitmap bitmap =null;

            URLConnection conn;
            InputStream is;

            try {
                conn = new URL(url).openConnection();
                is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                bitmap = BitmapFactory.decodeStream(bis);
                is.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            progressBar.setVisibility(View.GONE);
            image.setImageBitmap(bitmap);
        }
    }

}
