package com.example.eplpredictor.ui.login;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.eplpredictor.R;
import com.example.eplpredictor.ui.DashboardActivity;
import com.example.eplpredictor.utils.SharedPreferencesManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;




/**
 * Created by aakash on 11,April,2019
 */
//public class  LoginActivity extends AppCompatActivity {
//
//    // TAG is for show some tag logs in LOG screen.
//    public static final String TAG = "MainActivity";
//
//    // Request sing in code. Could be anything as you required.
//    public static final int RequestSignInCode = 7;
//
//    // Firebase Auth Object.
//    public FirebaseAuth firebaseAuth;
//
//    // Google API Client object.
//    public GoogleApiClient googleApiClient;
//
//    // Sing out button.
//
//    // Google Sign In button .
//    com.google.android.gms.common.SignInButton signInButton;
//
//    // TextView to Show Login User Email and Name.
//    TextView LoginUserName, LoginUserEmail;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        signInButton = (SignInButton) findViewById(R.id.google_sign_in);
//
//
//
//        LoginUserName = (TextView) findViewById(R.id.profile_name2);
//
//        LoginUserEmail = (TextView) findViewById(R.id.profile_email2);
//
//        signInButton = (com.google.android.gms.common.SignInButton)findViewById(R.id.google_sign_in);
//
//        // Getting Firebase Auth Instance into firebaseAuth object.
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        // Hiding the TextView on activity start up time.
//        LoginUserEmail.setVisibility(View.GONE);
//        LoginUserName.setVisibility(View.GONE);
//
//        // Creating and Configuring Google Sign In object.
//        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.web_client_id))
//                .requestEmail()
//                .build();
//
//        // Creating and Configuring Google Api Client.
//        googleApiClient = new GoogleApiClient.Builder(LoginActivity.this)
//                .enableAutoManage(LoginActivity.this , new GoogleApiClient.OnConnectionFailedListener() {
//                    @Override
//                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//                    }
//                } /* OnConnectionFailedListener */)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
//                .build();
//
//
//        // Adding Click listener to User Sign in Google button.
//        signInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                UserSignInMethod();
//
//            }
//        });
//
//        // Adding Click Listener to User Sign Out button.
//
//    }
//
//
//    // Sign In function Starts From Here.
//    public void UserSignInMethod(){
//
//        // Passing Google Api Client into Intent.
//        Intent AuthIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
//
//        startActivityForResult(AuthIntent, RequestSignInCode);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == RequestSignInCode){
//
//            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//
//            if (googleSignInResult.isSuccess()){
//
//                GoogleSignInAccount googleSignInAccount = googleSignInResult.getSignInAccount();
//
//                FirebaseUserAuth(googleSignInAccount);
//            }
//
//        }
//    }
//
//    public void FirebaseUserAuth(GoogleSignInAccount googleSignInAccount) {
//
//        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
//
//        Toast.makeText(LoginActivity.this,""+ authCredential.getProvider(),Toast.LENGTH_LONG).show();
//
//        firebaseAuth.signInWithCredential(authCredential)
//                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener() {
//                    @Override
//                    public void onComplete(@NonNull Task AuthResultTask) {
//
//                        if (AuthResultTask.isSuccessful()){
//
//                            // Getting Current Login user details.
//                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//
//                            // Showing Log out button.
//
//                            // Hiding Login in button.
//                            signInButton.setVisibility(View.GONE);
//
//                            // Showing the TextView.
//                            LoginUserEmail.setVisibility(View.VISIBLE);
//                            LoginUserName.setVisibility(View.VISIBLE);
//
//                            // Setting up name into TextView.
//                            LoginUserName.setText("NAME =  "+ firebaseUser.getDisplayName().toString());
//
//                            // Setting up Email into TextView.
//                            LoginUserEmail.setText("Email =  "+ firebaseUser.getEmail().toString());
//
//                        }else {
//                            Toast.makeText(LoginActivity.this,"Something Went Wrong",Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//    }
//
//}

public class LoginActivity extends AppCompatActivity implements LoginContract.View, GoogleApiClient.ConnectionCallbacks,
GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final int SIGN_IN_CODE = 9001;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private String idToken;
    public SharedPreferencesManager sharedPreferencesManager;
    private Context context;

    private Uri photoUri;
    private String name, email;
    private String photo;


    private SignInButton signInButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signInButton = findViewById(R.id.google_sign_in);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setOnClickListener(LoginActivity.this);

        firebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(LoginActivity.this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();



        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    createFirebaseHelper();
                    Log.d(TAG, "onAuthStateChangedSignIn :" + firebaseUser.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged: sign out");
                }
            }
        };


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN_CODE);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_CODE) {
            GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            performSignIn(signInResult);
        }
    }

    private void performSignIn(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount googleSignInAccount = result.getSignInAccount();
            idToken=googleSignInAccount.getIdToken();

            name=googleSignInAccount.getDisplayName();
            email=googleSignInAccount.getEmail();
            AuthCredential credential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
            firebaseAuthFromGoogle(credential);
        } else {
            Toast.makeText(this, getString(R.string.login_error), Toast.LENGTH_LONG).show();
        }
    }

    private void firebaseAuthFromGoogle(AuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            goToDashboard();
                            finish();
                        } else {
                            task.getException().printStackTrace();
                            Toast.makeText(LoginActivity.this, getString(R.string.auth_fail_error),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void goToDashboard() {
        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void createFirebaseHelper() {
       // final String encodedEmail = Utils.encodeEmail(email.toLowerCase());
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void showSuccessMessage(String message) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }
}
