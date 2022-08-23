package com.itcraftsolution.expansemanager.Fragments;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.itcraftsolution.expansemanager.Adapters.RvLatestEntryAdapter;
import com.itcraftsolution.expansemanager.Models.LatestEntry;
import com.itcraftsolution.expansemanager.R;
import com.itcraftsolution.expansemanager.databinding.FragmentDashboardBinding;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ArrayList<PieEntry> records;
    private SharedPreferences spf;
    private ArrayList<LatestEntry> list;
    private RvLatestEntryAdapter adapter;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(getLayoutInflater());

        spf = requireContext().getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        list = new ArrayList<>();


        getData();

        list.add(new LatestEntry(R.drawable.ic_baseline_restaurant_24, "Food", "₹", "400.00", "16 Sept", "2022", "12:34"));
        list.add(new LatestEntry(R.drawable.ic_baseline_restaurant_24, "Health", "₹", "100.00", "13 Sept", "2022", "09:34"));
        list.add(new LatestEntry(R.drawable.ic_baseline_restaurant_24, "Shopping", "₹", "2000.00", "12 Sept", "2022", "10:34"));
        list.add(new LatestEntry(R.drawable.ic_baseline_restaurant_24, "Travel", "₹", "500.00", "16 Sept", "2022", "12:34"));
        list.add(new LatestEntry(R.drawable.ic_baseline_restaurant_24, "Utilities", "₹", "50.00", "04 Sept", "2022", "12:34"));
        list.add(new LatestEntry(R.drawable.ic_baseline_restaurant_24, "Bonus", "₹", "1000.00", "01 Sept", "2022", "12:34"));
        list.add(new LatestEntry(R.drawable.ic_baseline_restaurant_24, "Salary", "₹", "20000.00", "27 Sept", "2022", "12:34"));

        adapter = new RvLatestEntryAdapter(requireContext(), list);
        binding.rvLatestEntry.setHasFixedSize(false);
        binding.rvLatestEntry.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvLatestEntry.setAdapter(adapter);


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

    private void getData()
    {
        if(spf.getBoolean("userProfile", false)) {
            String imgUrl = spf.getString("userImage", null);
            String name = spf.getString("userName", null);

            binding.txProfileName.setText(name);
            encodeImageStringBitmap(imgUrl);
        }
    }

    private void encodeImageStringBitmap(String encodeImageString)
    {
        byte[] encodeBytes = Base64.decode(encodeImageString, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeBytes, 0, encodeBytes.length);
        binding.igProfile.setImageBitmap(bitmap);
    }

    private void startAnimationCounter(int start, int end )
    {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(2000);
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