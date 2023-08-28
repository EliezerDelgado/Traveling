package com.travel_world.traveling.feature.home.activity;

import static com.travel_world.traveling.data.constants.Keys.KEY_USER;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.travel_world.traveling.R;
import com.travel_world.traveling.domain.User;
import com.travel_world.traveling.databinding.ActivityHomeBinding;
import com.travel_world.traveling.feature.home.fragments.HomeContentFragment;
import com.travel_world.traveling.feature.LilaActivity;
import com.travel_world.traveling.utils.Intents;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private User user;
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
            Bundle bundle = getIntent().getExtras();
            user = bundle.getParcelable(KEY_USER);
            addFragmentManager(user);
        }
        else
        {
            addFragmentManager();
        }
    }
    private void addFragmentManager(User user)
    {
        getSupportFragmentManager().beginTransaction().add(binding.homeFragmentLayout.getId(), HomeContentFragment.newInstance(user))
                .commitAllowingStateLoss();
    }
    //Todo Eliminar
    private void addFragmentManager()
    {
        getSupportFragmentManager().beginTransaction().add(binding.homeFragmentLayout.getId(), new HomeContentFragment())
                .commitAllowingStateLoss();
    }


    private void toolbarListener() {
        binding.homeToolbar.setOnMenuItemClickListener(item ->{
            if(item.getItemId() == R.id.menu_castle)
            {
                startActivity(Intents.openPage(getResources().getString(R.string.web_eurodisney)));
            } else if (item.getItemId() == R.id.menu_car) {
                startActivity(Intents.intentActivity(this, LilaActivity.class));
            }
            return true;
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        user = null;
    }
}