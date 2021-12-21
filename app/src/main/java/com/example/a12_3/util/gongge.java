package com.example.a12_3.util;

public class gongge {
    public static long lastClickTime;
    public static boolean isFirst(){
        long time=System.currentTimeMillis();
        long mtime=time-lastClickTime;
        if (mtime>0 && mtime<800){
            return true;
        }
        lastClickTime=time;
        return false;
    }
}
