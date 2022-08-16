package com.itcraftsolution.expansemanager;

import androidx.appcompat.app.AppCompatActivity;
import com.itcraftsolution.expansemanager.databinding.ActivitySpalshBinding;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class SpalshActivity extends AppCompatActivity {

    private ActivitySpalshBinding binding;
    private SharedPreferences spf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpalshBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        spf = getSharedPreferences("UserDetails", Context.MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(spf.getBoolean("userProfile", false))
                {
                    startActivity(new Intent(SpalshActivity.this, MainActivity.class));
                }else{
                    startActivity(new Intent(SpalshActivity.this, OnBoardingActivity.class));
                }
                finish();
            }
        }, 1100);
    }
}