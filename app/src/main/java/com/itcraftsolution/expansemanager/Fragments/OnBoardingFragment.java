package com.itcraftsolution.expansemanager.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itcraftsolution.expansemanager.Adapters.SliderAdapter;
import com.itcraftsolution.expansemanager.R;
import com.itcraftsolution.expansemanager.databinding.FragmentOnBoardingBinding;

public class OnBoardingFragment extends Fragment {

    private FragmentOnBoardingBinding binding;
    private SliderAdapter sliderAdapter;

    public OnBoardingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardingBinding.inflate(getLayoutInflater());

        sliderAdapter = new SliderAdapter(requireContext());
        binding.vpSlider.setAdapter(sliderAdapter);
        binding.dotsIndicator.setViewPager2(binding.vpSlider);

        binding.btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frContainer, new UserDetailsFragment())
                        .addToBackStack(null);
                fragmentTransaction.commit();
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

        return binding.getRoot();
    }
}