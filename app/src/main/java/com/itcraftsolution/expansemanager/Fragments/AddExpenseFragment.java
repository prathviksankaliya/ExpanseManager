package com.itcraftsolution.expansemanager.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itcraftsolution.expansemanager.Adapters.RvAddExpenseAdapter;
import com.itcraftsolution.expansemanager.Models.Category;
import com.itcraftsolution.expansemanager.R;
import com.itcraftsolution.expansemanager.databinding.FragmentAddExpenseBinding;

import java.util.ArrayList;

public class AddExpenseFragment extends Fragment {

    public AddExpenseFragment() {
        // Required empty public constructor
    }
    private FragmentAddExpenseBinding binding;
    private RvAddExpenseAdapter adapter;
    private ArrayList<Category> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddExpenseBinding.inflate(getLayoutInflater());

        requireActivity().findViewById(R.id.bottomAppBar).setVisibility(View.GONE);
        requireActivity().findViewById(R.id.fabAdd).setVisibility(View.GONE);
        list = new ArrayList<>();

        list.add(new Category(R.drawable.pieicon, "Food"));
        list.add(new Category(R.drawable.pieicon, "Health"));
        list.add(new Category(R.drawable.pieicon, "Shopping"));
        list.add(new Category(R.drawable.pieicon, "Income"));
        list.add(new Category(R.drawable.pieicon, "Travel"));
        list.add(new Category(R.drawable.pieicon, "Expense"));
        list.add(new Category(R.drawable.pieicon, "Utilities"));
        list.add(new Category(R.drawable.pieicon, "Others"));

        adapter = new RvAddExpenseAdapter(requireContext(), list);
        binding.rvCategory.setLayoutManager(new GridLayoutManager(requireContext(), 4));
        binding.rvCategory.setAdapter(adapter);


        return binding.getRoot();
    }
}