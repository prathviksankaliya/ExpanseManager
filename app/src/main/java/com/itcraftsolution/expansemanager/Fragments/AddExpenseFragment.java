package com.itcraftsolution.expansemanager.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itcraftsolution.expansemanager.R;
import com.itcraftsolution.expansemanager.databinding.FragmentAddExpenseBinding;

public class AddExpenseFragment extends Fragment {

    public AddExpenseFragment() {
        // Required empty public constructor
    }
    private FragmentAddExpenseBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddExpenseBinding.inflate(getLayoutInflater());

        requireActivity().findViewById(R.id.bottomAppBar).setVisibility(View.GONE);
        requireActivity().findViewById(R.id.fabAdd).setVisibility(View.GONE);

        return binding.getRoot();
    }
}