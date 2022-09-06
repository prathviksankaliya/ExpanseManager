package com.itcraftsolution.expansemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationBarView;
import com.itcraftsolution.expansemanager.Fragments.AddExpenseFragment;
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

        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setSelectedItemId(R.id.menuDashboard);

        getSupportFragmentManager().beginTransaction().replace(R.id.frMainContainer, new DashboardFragment()).commit();
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menuDashboard)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frMainContainer, new DashboardFragment()).addToBackStack(null).commit();
                }else if(item.getItemId() == R.id.menuReport){

                    getSupportFragmentManager().beginTransaction().replace(R.id.frMainContainer, new StatesticsFragment()).addToBackStack(null).commit();
                }
                return true;
            }
        });

        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frMainContainer, new AddExpenseFragment()).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public void onBackPressed() {

        if(binding.bottomNavigationView.getSelectedItemId() == R.id.menuDashboard)
        {
            super.onBackPressed();
        }
        else {
            binding.bottomNavigationView.setSelectedItemId(R.id.menuDashboard);
        }

    }
}