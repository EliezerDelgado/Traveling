package com.travel_world.traveling.feature.home.activity;

import static com.travel_world.traveling.data.constants.Keys.KEY_USER;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.travel_world.traveling.R;
import com.travel_world.traveling.domain.User;
import com.travel_world.traveling.databinding.ActivityHomeBinding;
import com.travel_world.traveling.feature.home.fragments.LilaFragment;
import com.travel_world.traveling.feature.home.viewmodel.UserHomeViewModel;
import com.travel_world.traveling.utils.Intents;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getIntentExtras();
        toolbarListener();
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
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        UserHomeViewModel.getInstance().getUser().removeObservers(this);
        UserHomeViewModel.getInstance().destroy();
    }
}