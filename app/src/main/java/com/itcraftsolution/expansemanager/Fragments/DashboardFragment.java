package com.itcraftsolution.expansemanager.Fragments;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.itcraftsolution.expansemanager.R;
import com.itcraftsolution.expansemanager.databinding.FragmentDashboardBinding;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ArrayList<PieEntry> records;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(getLayoutInflater());

        binding.btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().replace(R.id.frMainContainer, new UserDetailsFragment()).commit();
            }
        });
//        records = new ArrayList<>();
//        records.add(new PieEntry(32, "Food"));
//        records.add(new PieEntry(12, "Subscription"));
//        records.add(new PieEntry(8, "Water"));
//        records.add(new PieEntry(20, "Travel"));
//
//        PieDataSet pieDataSet = new PieDataSet(records, "Pie Chart");
//        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
//        pieDataSet.setValueTextColor(Color.BLACK);
//        pieDataSet.setValueTextSize(22);
//
//        PieData pieData = new PieData(pieDataSet);
//        binding.dashPieChart.setData(pieData);
//        binding.dashPieChart.getDescription().setEnabled(true);
//        binding.dashPieChart.setCenterText("Income Chart");
//        binding.dashPieChart.animate();

            startAnimationCounter(0,67);

        return binding.getRoot();
    }

    private void startAnimationCounter(int start, int end )
    {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                binding.txProgressDigit.setText(String.valueOf(valueAnimator.getAnimatedValue().toString() + "%"));
                binding.pbDashMoney.setProgress(Integer.parseInt(valueAnimator.getAnimatedValue().toString()));
            }
        });
        animator.start();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}