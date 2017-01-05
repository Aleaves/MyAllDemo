package com.app.liulongbing.myalldemo.download;

import java.util.List;

/**
 * Created by liulongbing on 16/12/28.
 */

public interface ThreadDao {

    /**
     * 插入线程信息
     * @param threadInfo
     */
    public void insertThread(ThreadInfo threadInfo);

    /**
     * 删除线程信息
     * @param url
     * @param thread_id
     */
    public void deleteThread(String url,int thread_id);


    /**
     * 更新线程的进度
     * @param url
     * @param thread_id
     * @param finished
     */
    public void updateThread(String url,int thread_id,int finished);


    public List<ThreadInfo> getThreads(String url);

    public boolean isExists(String url, int thread_id);

}
