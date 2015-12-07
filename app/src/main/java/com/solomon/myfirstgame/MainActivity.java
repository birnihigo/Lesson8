package com.solomon.myfirstgame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    private SplashPage sp;
    private boolean locked = false;
    private boolean dialogLocked = false;
    public static final String PREF_NAME = "MyPrefsFile";
    public static final String GAME_STATUS = "Status";
    public static final String GAME_PLAYER = "Player";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        FrameLayout Game = new FrameLayout(this);
        sp = new SplashPage(this);

        Game.addView(sp);
        setContentView(Game);
    }
    public void startGame(){
        if(!locked){
            locked = true;
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
            this.finish();
        }
    }
    public void resetGame(){
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(GAME_PLAYER, "Yellow");
        editor.putString(GAME_STATUS, "New");
        editor.commit();
    }
    protected void showMsgDialog_new(){
        if(!dialogLocked){
            dialogLocked = true;

            final Dialog dialog = new Dialog(this);
            LinearLayout l1 = new LinearLayout(this);
            l1.setGravity(Gravity.CENTER_HORIZONTAL);

            Button okBtn = new Button(this);
            okBtn.setWidth(300);
            okBtn.setText("OK");
            okBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetGame();;
                    dialogLocked = false;
                    dialog.dismiss();
                }
            });
            Button cancelBtn = new Button(this);
            cancelBtn.setWidth(300);
            cancelBtn.setText("Cancel");
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogLocked = false;
                    dialog.dismiss();
                }
            });
            l1.addView(okBtn);
            l1.addView(cancelBtn);
            dialog.setContentView(l1);
            dialog.setTitle("Do you really want to reset the game?");
            dialog.show();

        }
    }
    @Override
    protected void onRestart()
    {
        super.onRestart();
        sp.onRestart();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        sp.onStart();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        sp.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        sp.onPause();
        finish();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        sp.onStop();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        sp.onDestroy();
    }
}
