package com.itcraftsolution.expansemanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.itcraftsolution.expansemanager.Adapters.SliderAdapter;
import com.itcraftsolution.expansemanager.Fragments.UserDetailsFragment;
import com.itcraftsolution.expansemanager.databinding.ActivityOnBoardingBinding;

public class OnBoardingActivity extends AppCompatActivity {

    private ActivityOnBoardingBinding binding;
    private SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sliderAdapter = new SliderAdapter(this);
        binding.vpSlider.setAdapter(sliderAdapter);
        binding.dotsIndicator.setViewPager2(binding.vpSlider);

        binding.btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.frContainer.setVisibility(View.VISIBLE);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.frContainer, new UserDetailsFragment());
                fragmentTransaction.commit();
                binding.btnSkip.setVisibility(View.GONE);
                binding.vpSlider.setVisibility(View.GONE);
                binding.dotsIndicator.setVisibility(View.GONE);
            }
        });
        binding.vpSlider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(position == 2)
                {
                    binding.btnSkip.setText("Next");
                }else{
                    binding.btnSkip.setText("Skip");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

}