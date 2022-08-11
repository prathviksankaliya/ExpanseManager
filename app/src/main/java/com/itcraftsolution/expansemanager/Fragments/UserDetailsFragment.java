package com.itcraftsolution.expansemanager.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.itcraftsolution.expansemanager.Dialogs.CurrencyDialog;
import com.itcraftsolution.expansemanager.Dialogs.ThemeDialog;
import com.itcraftsolution.expansemanager.R;
import com.itcraftsolution.expansemanager.databinding.FragmentUserDetailsBinding;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
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
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserDetailsBinding.inflate(getLayoutInflater());

        spf = requireContext().getSharedPreferences("UserDetails", Context.MODE_PRIVATE);


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

        binding.igEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkStoragePermission())
                {
                    getImage.launch("image/*");
                }else{
                    requestStoragePermission();
                }

//                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//                photoPickerIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(Intent.createChooser(photoPickerIntent, "Select Picture"), PERMISSION_ID);
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

                    Glide.with(requireContext()).load(photoUri).into(binding.igProfile);

//                    try {
//                        InputStream inputStream = requireContext().getContentResolver().openInputStream(photoUri);
//                        bitmap = BitmapFactory.decodeStream(inputStream);
//                        encodeBitmapImage(bitmap);
//                        CheckImage = true;
//
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }



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


    private void encodeBitmapImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, byteArrayOutputStream);
        binding.igEditProfile.setImageBitmap(bitmap);
        byte[] bytesofimage = byteArrayOutputStream.toByteArray();
        encodeImageString = android.util.Base64.encodeToString(bytesofimage, Base64.DEFAULT);
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(requireContext(), "run", Toast.LENGTH_SHORT).show();
        binding.txCurrency.setText(spf.getString("currency", null));
    }
}