package com.myuntils;

/**
 * Created by Administrator on 2016/11/23/023.
 * 网络请求回调接口
 */

public interface TaskDelegate {
    /**
     * 请求完成的回调接口
     * @param flag
     * @param result
     */
    void taskDone(int flag,Object result);
    /**
     * 异步网络数据请求
     * @param flag
     * @param params
     * @return
     * @throws Exception
     */
    Object doTask(int flag,Object... params) throws Exception;
}
