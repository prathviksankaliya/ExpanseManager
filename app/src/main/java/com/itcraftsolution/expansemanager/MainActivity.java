package com.itcraftsolution.expansemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;
import com.itcraftsolution.expansemanager.Fragments.DashboardFragment;
import com.itcraftsolution.expansemanager.Fragments.StatesticsFragment;
import com.itcraftsolution.expansemanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setSelectedItemId(R.id.menuDashboard);

        getSupportFragmentManager().beginTransaction().replace(R.id.frMainContainer, new DashboardFragment()).commit();
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menuDashboard)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frMainContainer, new DashboardFragment()).commit();
                }else if(item.getItemId() == R.id.menuReport){

                    getSupportFragmentManager().beginTransaction().replace(R.id.frMainContainer, new StatesticsFragment()).commit();
                }
                return true;
            }
        });
    }
}