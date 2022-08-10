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

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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
//                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
//
//                builder.setTitle("Choose any Theme");
//                builder.setSingleChoiceItems(singleItems, checkedItem, new DialogInterface
//                        .OnClickListener() {
//                    public void onClick(DialogInterface dialog, int item) {
//                        Toast.makeText(requireContext(), ""+ singleItems[item], Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    }
//                });
//                AlertDialog alert = builder.create();
//                alert.show();

                ThemeDialog dialog = new ThemeDialog(requireContext());
                dialog.setCancelable(false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.getWindow().setGravity(Gravity.TOP);
                dialog.show();
            }
        });
        return binding.getRoot();
    }
}