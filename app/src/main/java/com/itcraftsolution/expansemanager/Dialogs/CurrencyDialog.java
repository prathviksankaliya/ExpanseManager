package com.itcraftsolution.expansemanager.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;
import com.itcraftsolution.expansemanager.R;
import com.itcraftsolution.expansemanager.databinding.CurrencyDialogSampleBinding;
import com.itcraftsolution.expansemanager.databinding.ThemeDialogSampleBinding;

public class CurrencyDialog extends Dialog {

    private Context context;
    private CurrencyDialogSampleBinding binding;
    private SharedPreferences spf;

    public CurrencyDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CurrencyDialogSampleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                spf = context.getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
                int checkRadiobuttonId = radioGroup.getCheckedRadioButtonId();

                RadioButton selectedBtn = radioGroup.findViewById(checkRadiobuttonId);
                if(selectedBtn.isChecked())
                {
                    SharedPreferences.Editor editor = spf.edit();
                    editor.putString("currency", selectedBtn.getText().toString());
                    editor.apply();
                }
                dismiss();
            }
        });
    }
}
