package com.itcraftsolution.expansemanager.Fragments;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.itcraftsolution.expansemanager.MainActivity;
import com.itcraftsolution.expansemanager.R;
import com.itcraftsolution.expansemanager.databinding.FragmentUserDetailsBinding;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;


public class UserDetailsFragment extends Fragment {


    public UserDetailsFragment() {
        // Required empty public constructor
    }

    private FragmentUserDetailsBinding binding;
    private String[] singleItems = {"Item 1", "Item 2", "Item 3"};
    private int checkedItem = -1;
    private static final int PERMISSION_ID = 44;
    private ActivityResultLauncher<String> getImage;
    private String destPath, encodeImageString;
    private Uri photoUri;
    private  SharedPreferences spf;
    private Bitmap bitmap;
    private boolean CheckImage = false;
    private Dialog dialogCurrency, dialogTheme;
    private RadioGroup rdGropeCurrency, rdGropeTheme;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserDetailsBinding.inflate(getLayoutInflater());

        spf = requireContext().getSharedPreferences("UserDetails", Context.MODE_PRIVATE);
        dialogCurrency = new Dialog(requireContext());
        dialogCurrency.setContentView(R.layout.currency_dialog_sample);
        rdGropeCurrency = dialogCurrency.findViewById(R.id.rdGroup);
        dialogTheme = new Dialog(requireContext());
        dialogTheme.setContentView(R.layout.theme_dialog_sample);
        rdGropeTheme = dialogTheme.findViewById(R.id.rdGroup);

        getProfile();

        binding.llTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialogTheme();
            }
        });

        binding.llCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialogCurrency();
            }
        });

        binding.igEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkStoragePermission())
                {
                    getImage.launch("image/*");
                }else{
                    requestStoragePermission();
                }
            }
        });

        binding.igBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spf.getBoolean("userProfile", false))
                {
                    getParentFragmentManager().beginTransaction().replace(R.id.frMainContainer, new DashboardFragment()).addToBackStack(null).commit();
                }else{
                    getParentFragmentManager().beginTransaction().replace(R.id.frContainer, new OnBoardingFragment()).addToBackStack(null).commit();
                }
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.edNameText.getText().toString().length() <= 2)
                {
                    Snackbar.make(binding.layout,"Name must be Minimum 4 characters", Snackbar.LENGTH_LONG)
                            .setBackgroundTint(getResources().getColor(R.color.red))
                            .setTextColor(getResources().getColor(R.color.white))
                            .show();
                    binding.edNameText.requestFocus();
                }else if(spf.getString("userImage", null) == null || binding.igProfile.getDrawable() == null)
                {
                    Snackbar.make(binding.layout,"Please set your profile picture", Snackbar.LENGTH_LONG)
                            .setBackgroundTint(getResources().getColor(R.color.red))
                            .setTextColor(getResources().getColor(R.color.white))
                            .show();
                    binding.igProfile.requestFocus();
                }
                else{
                    SharedPreferences.Editor editor = spf.edit();
                    editor.putString("userName", binding.edNameText.getText().toString());
                    editor.putString("userCurrencyText", binding.txCurrency.getText().toString());
                    editor.putBoolean("userProfile", true);
                    editor.apply();
                    Toast.makeText(requireContext(), "Profile Saved", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(requireContext(), MainActivity.class);
                    startActivity(intent);
                    requireActivity().finishAffinity();
                }
            }
        });

        getImage = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if(result.getPath() != null)
                {
                    photoUri = result;
                    destPath =  UUID.randomUUID().toString() + ".png";

                    UCrop.Options options =new UCrop.Options();
                    options.setCompressionQuality(90);
                    options.setCircleDimmedLayer(true);
                    options.setCompressionFormat(Bitmap.CompressFormat.PNG);

                    UCrop.of(photoUri, Uri.fromFile(new File(requireContext().getCacheDir(), destPath)))
                            .withOptions(options)
                            .withAspectRatio(0,0)
                            .useSourceImageAspectRatio()
                            .withMaxResultSize(1080, 720)
                            .start(requireContext(), UserDetailsFragment.this);

//                    Glide.with(requireContext()).load(photoUri).into(binding.igProfile);

                    try {
                        InputStream inputStream = requireContext().getContentResolver().openInputStream(photoUri);
                        bitmap = BitmapFactory.decodeStream(inputStream);
                        encodeBitmapImageString(bitmap);
                        CheckImage = true;

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        
        return binding.getRoot();
    }

    //Request For Permission
    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_ID);
    }

    // Check Permission
    private boolean checkStoragePermission() {
        return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void encodeBitmapImageString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, byteArrayOutputStream);
        binding.igProfile.setImageBitmap(bitmap);
        byte[] bytesofimage = byteArrayOutputStream.toByteArray();
        encodeImageString = android.util.Base64.encodeToString(bytesofimage, Base64.DEFAULT);
        SharedPreferences.Editor editor = spf.edit();
        editor.putString("userImage", encodeImageString);
        editor.apply();
    }

    private void encodeImageStringBitmap(String encodeImageString)
    {
        byte[] encodeBytes = Base64.decode(encodeImageString, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeBytes, 0, encodeBytes.length);
        binding.igProfile.setImageBitmap(bitmap);
    }


    private void getDialogCurrency()
    {

        dialogCurrency.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogCurrency.show();

        rdGropeCurrency.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int checkRadiobuttonId = radioGroup.getCheckedRadioButtonId();

                RadioButton selectedBtn = radioGroup.findViewById(checkRadiobuttonId);
                if(selectedBtn.isChecked())
                {
                    selectedBtn.setChecked(true);
                    binding.txCurrency.setText(selectedBtn.getText());
                    SharedPreferences.Editor editor = spf.edit();
                    editor.putInt("userCurrency", i);
                    editor.apply();
                    dialogCurrency.dismiss();
                }
            }
        });

    }

    private void getDialogTheme()
    {
        dialogTheme.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogTheme.show();


        rdGropeTheme.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int checkRadiobuttonId = radioGroup.getCheckedRadioButtonId();

                RadioButton selectedBtn = radioGroup.findViewById(checkRadiobuttonId);
                if(selectedBtn.isChecked())
                {
                    SharedPreferences.Editor editor = spf.edit();
                    if(selectedBtn.getText().toString().equals("Light Theme"))
                    {
                        selectedBtn.setChecked(true);
                        editor.putInt("userTheme", i);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        requireActivity().recreate();
                    }else if(selectedBtn.getText().toString().equals("Dark Theme")){
                        selectedBtn.setChecked(true);
                        editor.putInt("userTheme", i);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        requireActivity().recreate();
                    }else{
                        selectedBtn.setChecked(true);
                        editor.putInt("userTheme", i);
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                        requireActivity().recreate();
                    }

                    editor.apply();
                    dialogTheme.dismiss();
                }
            }
        });
    }

    private void getProfile()
    {
        if(spf.getBoolean("userProfile", false))
        {
            String imgUrl = spf.getString("userImage", null);
            String name = spf.getString("userName", null);
            String currencyText = spf.getString("userCurrencyText", null);
            int currency = spf.getInt("userCurrency", 0);
            int theme = spf.getInt("userTheme", 0);

            encodeImageStringBitmap(imgUrl);
            binding.edNameText.setText(name);
            binding.txCurrency.setText(currencyText);

            if(currency == 0)
            {
                rdGropeCurrency.check(R.id.rdRupee);
            }else{
                rdGropeCurrency.check(currency);
            }
            if(theme == 0)
            {
                rdGropeTheme.check(R.id.rdDeviceTheme);
            }else{
                rdGropeTheme.check(theme);
            }

        }
    }

}