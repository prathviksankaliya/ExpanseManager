package com.itcraftsolution.expansemanager.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itcraftsolution.expansemanager.R;
import com.itcraftsolution.expansemanager.databinding.FragmentDashboardBinding;
import com.itcraftsolution.expansemanager.databinding.FragmentStatesticsBinding;

public class StatesticsFragment extends Fragment {

    private FragmentStatesticsBinding binding;

    public StatesticsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStatesticsBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }
}