package com.solomon.myfirstgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by user on 12/6/2015.
 */
public class SplashPage extends SurfaceView {
    private Bitmap mTitle;
    private Bitmap mYellow_coin;
    private Bitmap mRed_coin;
    private SurfaceHolder mHolder;
    private SplashThread mSt = null;
    private MainActivity mParentActivity;

    public SplashPage(Context context){
        super(context);
        mParentActivity = (MainActivity)context;
        mHolder = getHolder();
        mHolder.addCallback(new SurfaceHolder.Callback(){

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                makeGraphics();
                makeThread();
                mSt.setRunning(true);
                mSt.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }
    private void makeGraphics(){
        mTitle = BitmapFactory.decodeResource(getResources(), R.drawable.tictactoe);
        mRed_coin = BitmapFactory.decodeResource(getResources(), R.drawable.red_coin);
        mYellow_coin = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_coin);
    }
    private void makeThread(){
        mSt = new SplashThread(this);
    }
    public void killThread(){
        boolean retry = true;
        mSt.setRunning(false);
        while(retry){
            try{
                mSt.join();
                retry = false;
            }catch (InterruptedException e){

            }
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getX() > 0 && event.getY() > 0){
            mSt.setRunning(false);
            mParentActivity.startGame();
        }
        return true;
    }
    @Override
    public void onDraw(Canvas canvas){
        canvas.drawColor(Color.WHITE);
        //canvas.drawText("TICTACTOE GAME", 100.0f, 10.0f, null);
        canvas.drawBitmap(mTitle, (this.getWidth() - mTitle.getWidth()) / 2.0f, this.getHeight()/2.0f - 40.0f, null);
        canvas.drawBitmap(mRed_coin, (this.getWidth() - mRed_coin.getWidth())/2.0f + 10.0f, mTitle.getHeight()+5, null);
        canvas.drawBitmap(mYellow_coin, (this.getWidth() - mRed_coin.getWidth() - mYellow_coin.getWidth())/2.0f, mTitle.getHeight()+5, null);
    }
    public void onRestart()
    {

    }

    public void onStart()
    {

    }

    public void onResume()
    {
        makeThread();
    }

    public void onPause()
    {
        killThread();

    }

    public void onStop()
    {

    }

    public void onDestroy()
    {
        mTitle.recycle();
        mTitle = null;
        mRed_coin.recycle();
        mRed_coin = null;
        mYellow_coin.recycle();
        mYellow_coin = null;
        mHolder.removeCallback(null);
        mHolder = null;
        System.gc();
    }

}
