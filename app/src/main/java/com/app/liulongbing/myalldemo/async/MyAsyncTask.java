package com.app.liulongbing.myalldemo.async;

import android.os.AsyncTask;

/**
 * Created by liulongbing on 16/12/27.
 */

public class MyAsyncTask extends AsyncTask<Void,Void,Void>{

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        return null;

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }


}
