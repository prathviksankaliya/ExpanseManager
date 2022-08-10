package com.itcraftsolution.expansemanager.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.itcraftsolution.expansemanager.Dialogs.CurrencyDialog;
import com.itcraftsolution.expansemanager.Dialogs.ThemeDialog;
import com.itcraftsolution.expansemanager.R;
import com.itcraftsolution.expansemanager.databinding.FragmentUserDetailsBinding;


public class UserDetailsFragment extends Fragment {


    public UserDetailsFragment() {
        // Required empty public constructor
    }

    private FragmentUserDetailsBinding binding;
    private String[] singleItems = {"Item 1", "Item 2", "Item 3"};
    private int checkedItem = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserDetailsBinding.inflate(getLayoutInflater());

        binding.llTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ThemeDialog dialog = new ThemeDialog(requireContext());
                dialog.setCancelable(false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        binding.llCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrencyDialog dialog = new CurrencyDialog(requireContext());
                dialog.setCancelable(false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.edNameText.getText().toString().length() <= 2)
                {
                    Snackbar.make(binding.layout,"Name must be Minimum 4 characters", Snackbar.LENGTH_LONG)
                            .setBackgroundTint(getResources().getColor(R.color.red))
                            .setTextColor(getResources().getColor(R.color.white))
                            .show();
                    binding.edNameText.requestFocus();
                }else{
                    Toast.makeText(requireContext(), "Name : "+binding.edNameText.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return binding.getRoot();
    }
}