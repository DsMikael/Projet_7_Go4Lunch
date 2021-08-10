package com.mdasilva.go4lunch.ui.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.mdasilva.go4lunch.R;
import com.mdasilva.go4lunch.databinding.ActivityLoginBinding;
import com.mdasilva.go4lunch.ui.viewModel.LoginActivityViewModel;

import timber.log.Timber;

public class LoginActivity extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;
    private LoginActivityViewModel viewModel;
    private final CallbackManager mCallbackManager = CallbackManager.Factory.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        viewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        LoginManager.getInstance().registerCallback(
                mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Timber.d("Facebook OnSuccess");
                        viewModel.handleFacebookAccessToken(loginResult.getAccessToken());
                    }
                    @Override
                    public void onCancel() {
                        Timber.d("Facebook OnCancel");
                    }
                    @Override
                    public void onError(FacebookException error) {
                        Timber.w(error, "Facebook Error");}
                });


        ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        viewModel.signInGoogle(data);
                    }
                });

        binding.btnFb.setOnClickListener(v -> viewModel.signInFacebook(this));

        binding.btnGoogle.setOnClickListener(v -> {
            Intent intent = mGoogleSignInClient.getSignInIntent();
            mStartForResult.launch(intent);
        });

        binding.logout.setOnClickListener(v -> viewModel.signOut());

        viewModel.getUserMutableLiveData().observe(this, user -> {
            if(user != null) {
                Timber.d("Oberve User name %s", user.getDisplayName());
                Intent intent = new Intent(this, HomeActivity.class);
                finish();
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Timber.d("CallbackManager");
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }

}