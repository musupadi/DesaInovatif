package com.destinyapp.desainovatif.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.destinyapp.desainovatif.Activity.ui.HomeFragment;
import com.destinyapp.desainovatif.Activity.ui.UserFragment;
import com.destinyapp.desainovatif.R;

public class MainActivity extends AppCompatActivity {
    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    LinearLayout LHome,LUser;
    TextView THome,TUser;
    ImageView IHome,IUser;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Declaration();
        Default();
        Home();
        OnClick();
    }
    private void OnClick(){
        LHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Home();
            }
        });
        LUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User();
            }
        });
    }
    private void Declaration(){
        LHome = findViewById(R.id.linearHome);
        LUser = findViewById(R.id.linearUser);
        THome = findViewById(R.id.tvHome);
        TUser = findViewById(R.id.tvUser);
        IHome = findViewById(R.id.ivHome);
        IUser = findViewById(R.id.ivUser);
    }
    private void Default(){
        IHome.setImageResource(R.drawable.home);
        THome.setTextColor(Color.BLACK);
        IUser.setImageResource(R.drawable.user);
        TUser.setTextColor(Color.BLACK);
    }
    private void Home(){
        Default();
        IHome.setImageResource(R.drawable.home_active);
        THome.setTextColor(Color.rgb(83,210,220));
        fragment = new HomeFragment();
        ChangeFragment(fragment);
    }
    private void User(){
        Default();
        IUser.setImageResource(R.drawable.user_active);
        TUser.setTextColor(Color.rgb(83,210,220));
        fragment = new UserFragment();
        ChangeFragment(fragment);
    }
    private void ChangeFragment(Fragment fragment){
        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.Container,fragment);
            ft.commit();
        }
    }
}