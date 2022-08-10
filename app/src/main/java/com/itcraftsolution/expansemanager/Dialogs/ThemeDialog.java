package com.itcraftsolution.expansemanager.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import com.itcraftsolution.expansemanager.databinding.ThemeDialogSampleBinding;

public class ThemeDialog extends Dialog {

    Context context;
    public ThemeDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    private ThemeDialogSampleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ThemeDialogSampleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int checkRadiobuttonId = radioGroup.getCheckedRadioButtonId();

                RadioButton selectedBtn = radioGroup.findViewById(checkRadiobuttonId);
                if(selectedBtn.isChecked())
                {
                    Toast.makeText(context, ""+selectedBtn.getText().toString(), Toast.LENGTH_SHORT).show();
                    if(selectedBtn.getText().toString().equals("Light Theme"))
                    {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }else if(selectedBtn.getText().toString().equals("Dark Theme")){
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    }
                }

                dismiss();
            }
        });

    }
}
