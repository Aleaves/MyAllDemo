package com.app.liulongbing.myalldemo.download;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liulongbing on 16/12/28.
 */

public class ThreadDaoImp implements ThreadDao{

    private DBHelper mHelper = null;


    public ThreadDaoImp(Context context){
        mHelper = new DBHelper(context);
    }

    @Override
    public void insertThread(ThreadInfo threadInfo) {

        SQLiteDatabase db = mHelper.getWritableDatabase();

        db.execSQL("insert into thread_info (thread_id,url,start,end,finished) values(?,?,?,?,?)",
                new Object[]{threadInfo.getId(),threadInfo.getUrl(),threadInfo.getStart(),threadInfo.getEnd(),threadInfo.getFinished()}
                );

        db.close();

    }

    @Override
    public void deleteThread(String url, int thread_id) {

        SQLiteDatabase db = mHelper.getWritableDatabase();

        db.execSQL("delete from thread_info where url=? and thread_id =?",new Object[]{url,thread_id});


    }

    @Override
    public void updateThread(String url, int thread_id, int finished) {

        SQLiteDatabase db = mHelper.getWritableDatabase();

        db.execSQL("update thread_info set finished = ? where url =? and thread_id=?",
                new Object[]{finished,url,thread_id}
                );

        db.close();

    }

    @Override
    public List<ThreadInfo> getThreads(String url) {

        List<ThreadInfo> lists = new ArrayList<ThreadInfo>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from thread_info where url=?",new String[]{
            url
        });

        while (cursor.moveToNext()){
            ThreadInfo thread = new ThreadInfo();
            thread.setId(cursor.getInt(cursor.getColumnIndex("thread_id")));
            thread.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            thread.setFinished(cursor.getInt(cursor.getColumnIndex("finished")));
            thread.setStart(cursor.getInt(cursor.getColumnIndex("start")));
            thread.setEnd(cursor.getInt(cursor.getColumnIndex("end")));
            lists.add(thread);
        }

        db.close();

        return lists;
    }

    @Override
    public boolean isExists(String url, int thread_id) {

        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from thread_info where url=? and thread_id=?",new String[]{
                url, String.valueOf(thread_id)
        });

        boolean exits = cursor.moveToNext();

        db.close();

        return exits;
    }


}
