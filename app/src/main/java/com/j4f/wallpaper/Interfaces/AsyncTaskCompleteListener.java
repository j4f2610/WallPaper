package com.j4f.wallpaper.Interfaces;

/**
 * Created by pham on 7/13/2015.
 */
public interface AsyncTaskCompleteListener<T> {
    public void onTaskCompelete(T result);
    public void onError(int ErrorCode);
}
