package com.travel_world.traveling.feature.login.fragments;

import static android.Manifest.permission.POST_NOTIFICATIONS;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS;
import static com.travel_world.traveling.data.constants.Keys.CHANNEL_NOTIFICATION;
import static com.travel_world.traveling.data.constants.Keys.RESULT_LOGIN;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.travel_world.traveling.R;
import com.travel_world.traveling.data.constants.Keys;
import com.travel_world.traveling.databinding.FragmentLoginBinding;
import com.travel_world.traveling.domain.User;
import com.travel_world.traveling.feature.login.interfaces.OnListenerLogin;
import com.travel_world.traveling.utils.AlertDialogs;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private OnListenerLogin listener;
    private User user;
    private boolean showDialogPermission = true;
    private ActivityResultLauncher<String> notificationPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted ->
            {
                if (!isGranted) {
                    if (Build.VERSION.SDK_INT >= 33) {
                        if (shouldShowRequestPermissionRationale(POST_NOTIFICATIONS)) {
                            if(showDialogPermission) {
                                showNotificationPermissionRationale();
                                showDialogPermission = false;
                            }
                        } else {
                            if(showDialogPermission) {
                                showSettingDialog();
                                showDialogPermission = false;
                            }
                        }
                    }
                }
            });




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = new User();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        listener.ocultToolbar();
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        permissionLocation();
        buttonListener();
        inputListener();
    }

    private void permissionLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            notificationPermissionLauncher.launch(
                    POST_NOTIFICATIONS
            );
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnListenerLogin)
            listener = (OnListenerLogin) context;
        else
            throw new ClassCastException(context + " must implement listener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private void buttonListener() {
        binding.buttonLoginRegister.setOnClickListener(v ->
        {
            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_registerFragment);
            NavHostFragment.findNavController(this).getCurrentBackStackEntry().getSavedStateHandle().getLiveData(RESULT_LOGIN).observe(requireActivity(), o -> {
                user = (User) o;
                if (user != null) {
                    Log.d("ELI", user.getName());
                }
            });
        });
        binding.buttonLogin.setOnClickListener(v ->
                goToHomeActivity()
        );
        binding.buttonLoginForgot.setOnClickListener(v ->
                Snackbar.make(binding.constraintLayoutLoginFragment, getString(R.string.button_login_forgot_onclick), Snackbar.LENGTH_LONG).show()
        );
    }

    private void goToHomeActivity() {
        if (binding.nameTextLogin.getText() != null && binding.passwordTextLogin.getText() != null) {
            if (binding.nameTextLogin.getText().toString().equals(user.getName())
                    && binding.passwordTextLogin.getText().toString().equals(user.getPassword())) {
                LoginFragmentDirections.ActionLoginFragmentToHomeActivity action = LoginFragmentDirections.actionLoginFragmentToHomeActivity(user);
                sendNotificationLoginSuccess();
                NavHostFragment.findNavController(this).navigate(action);
            } else {
                showErrorLoginMessage();
            }
        }

    }



    private void sendNotificationLoginSuccess() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_NOTIFICATION)
                .setSmallIcon(R.drawable.ic_notifications_none)
                .setContentTitle(getString(R.string.notification_login_success_title, user.getName()))
                .setContentText(getString(R.string.notification_login_success_text))
                .setLargeIcon(BitmapFactory.decodeResource(requireContext().getResources(),
                        R.drawable.ic_login_success))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(requireContext().getResources(),
                                R.drawable.ic_login_success))
                        .bigLargeIcon((Bitmap) null));
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());
        if (ActivityCompat.checkSelfPermission(requireContext(), POST_NOTIFICATIONS) == PERMISSION_GRANTED || Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            notificationManager.notify(Keys.NOTIFICATION_ID_LOGIN_SUCCESS, builder.build());
        }
    }

    private void showErrorLoginMessage() {
        AlertDialogs.createSimpleInformativeDialog(requireActivity(),
                getResources().getString(R.string.error_login_incorrect_login_or_password),
                getResources().getString(R.string.confirm_message_login)
        ).show();
    }


    private void showSettingDialog() {
        new MaterialAlertDialogBuilder(requireContext(), com.google.android.material.R.style.MaterialAlertDialog_Material3)
                .setTitle(getString(R.string.alert_dialogo_permission_notification_setting_title))
                .setMessage(getString(R.string.alert_dialogo_permission_notification_setting_message))
                .setPositiveButton(getString(R.string.button_positive_text), (DialogInterface.OnClickListener) (dialog, which) -> {
                    Intent intent = new Intent(ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.parse("package:"+getActivity().getPackageName()));
                    startActivity(intent);

                })
                .setNegativeButton(getString(R.string.button_negative_text), null)
                .show();
    }

    private void showNotificationPermissionRationale() {
        new MaterialAlertDialogBuilder(requireContext(), com.google.android.material.R.style.MaterialAlertDialog_Material3)
                .setTitle("Alert")
                .setMessage(getString(R.string.alert_dialogo_permission_notification_rationale_title))
                .setPositiveButton(getString(R.string.button_positive_text), (DialogInterface.OnClickListener) (dialog, which) -> {
                    if (Build.VERSION.SDK_INT >= 33)
                        notificationPermissionLauncher.launch(POST_NOTIFICATIONS);
                })
                .setNegativeButton(getString(R.string.button_negative_text), null)
                .show();
    }

    private void inputListener() {
        binding.nameTextLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.passwordTextLogin.getText() != null) {
                    binding.buttonLogin.setEnabled(
                            !s.toString().isEmpty()
                                    && !binding.passwordTextLogin.getText().toString().isEmpty()
                    );
                }
            }
        });

        binding.passwordTextLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.nameTextLogin.getText() != null) {
                    binding.buttonLogin.setEnabled(
                            !s.toString().isEmpty()
                                    && !binding.nameTextLogin.getText().toString().isEmpty()
                    );
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        listener = null;
    }
}