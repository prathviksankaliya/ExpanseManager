package com.itcraftsolution.expansemanager;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.itcraftsolution.expansemanager.Adapters.SliderAdapter;
import com.itcraftsolution.expansemanager.Fragments.OnBoardingFragment;
import com.itcraftsolution.expansemanager.Fragments.UserDetailsFragment;
import com.itcraftsolution.expansemanager.databinding.ActivityOnBoardingBinding;

public class OnBoardingActivity extends AppCompatActivity {

    private ActivityOnBoardingBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.frContainer, new OnBoardingFragment()).addToBackStack(null).commit();

    }
}