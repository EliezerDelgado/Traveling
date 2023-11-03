package com.travel_world.traveling.feature.home.activity;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import com.travel_world.traveling.R;
import com.travel_world.traveling.databinding.ActivityHomeBinding;
import com.travel_world.traveling.domain.User;
import com.travel_world.traveling.feature.home.viewmodel.UserHomeViewModel;
import com.travel_world.traveling.utils.AlertDialogs;
import com.travel_world.traveling.utils.Intents;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private final ActivityResultLauncher<String[]> locationPermissionRequest =
            registerForActivityResult(new ActivityResultContracts
                            .RequestMultiplePermissions(), result -> {
                        Boolean fineLocationGranted = result.getOrDefault(
                                ACCESS_FINE_LOCATION, false);
                        Boolean coarseLocationGranted = result.getOrDefault(
                                ACCESS_COARSE_LOCATION,false);
                        if (fineLocationGranted != null && fineLocationGranted) {
                            // Precise location access granted.
                        } else if (coarseLocationGranted != null && coarseLocationGranted) {
                        } else {
                            AlertDialogs.createSimpleInformativeDialogWithOnCLickListener(this,
                                getString(R.string.error_permission_location), getString(R.string.universal_message_ok),
                                (dialog, which) -> {
                                    finish();
                                }).show();
                        }
                    }
            );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserHomeViewModel.start();
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getIntentExtras();
        toolbarListener();
        permissionLocation();
    }

    private void getIntentExtras() {
        if(getIntent().getExtras() != null){
            User u =HomeActivityArgs.fromBundle(getIntent().getExtras()).getArgUser();
            UserHomeViewModel.getInstance().getUser().setValue(u);
        }
    }
    private void toolbarListener() {
        binding.homeToolbar.setOnMenuItemClickListener(item ->{
            if(item.getItemId() == R.id.menu_castle)
            {
                startActivity(Intents.openPage(getResources().getString(R.string.web_eurodisney)));
            } else if (item.getItemId() == R.id.menu_car) {
                ((NavHostFragment)getSupportFragmentManager().findFragmentById(binding.navHostFragmentHome.getId())).getNavController().navigate(R.id.lilaFragment);
            }
            return true;
        });
    }
    private void permissionLocation()
    {
        locationPermissionRequest.launch(new String[] {
                ACCESS_FINE_LOCATION,
                ACCESS_COARSE_LOCATION
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        UserHomeViewModel.getInstance().getUser().removeObservers(this);
        UserHomeViewModel.getInstance().destroy();
    }
}