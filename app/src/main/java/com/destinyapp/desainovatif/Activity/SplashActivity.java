package com.destinyapp.desainovatif.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;

import com.destinyapp.desainovatif.R;
import com.destinyapp.desainovatif.SharedPreferance.DB_Helper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final DB_Helper dbHelper = new DB_Helper(SplashActivity.this);
        Cursor cursor = dbHelper.checkUser();
        if (cursor.getCount()>0){
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    changeActivity();
                }
            }, 3000); //3000 L = 3 detik
        }
    }
    private void changeActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}