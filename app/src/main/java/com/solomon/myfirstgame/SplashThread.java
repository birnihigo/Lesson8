package com.solomon.myfirstgame;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

/**
 * Created by user on 12/6/2015.
 */
public class SplashThread extends Thread{
    private SplashPage mView;
    private boolean mRunning = false;

    public SplashThread(SplashPage view){
        this.mView = view;
    }
    public void setRunning(boolean run){
        this.mRunning = run;
    }
    @SuppressLint("WrongCall")
    @Override
    public void run(){
        while(mRunning){
            Canvas c = null;
            try{
                c = mView.getHolder().lockCanvas();
                synchronized (mView.getHolder()){
                    mView.onDraw(c);
                }
            }
            finally {
                if(c != null){
                    mView.getHolder().unlockCanvasAndPost(c);
                }
            }

        }
    }
}
