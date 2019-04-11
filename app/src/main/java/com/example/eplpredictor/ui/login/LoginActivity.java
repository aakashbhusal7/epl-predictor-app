package com.example.eplpredictor.ui.login;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.eplpredictor.R;
import com.example.eplpredictor.databinding.ActivityMainBinding;
import com.example.eplpredictor.model.User;
import com.example.eplpredictor.ui.DashboardActivity;
import com.example.eplpredictor.utils.SharedPreferencesManager;
import com.example.eplpredictor.utils.Utils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
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

import java.util.HashMap;

/**
 * Created by aakash on 11,April,2019
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View, GoogleApiClient.ConnectionCallbacks,
GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final int SIGN_IN_CODE=1001;
    private static final String TAG=LoginActivity.class.getSimpleName();
    private String idToken;
    public SharedPreferencesManager sharedPreferencesManager;
    private Context context;

    private Uri photoUri;
    private String name, email;
    private String photo;

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        activityMainBinding.googleSignIn.setSize(SignInButton.SIZE_WIDE);
        activityMainBinding.googleSignIn.setOnClickListener(LoginActivity.this);
        firebaseAuth=FirebaseAuth.getInstance();
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                if(firebaseUser!=null){
                    createFirebaseHelper();
                    Log.d(TAG,"onAuthStateChangedSignIn :"+firebaseUser.getUid());
                }
                else{
                    Log.d(TAG,"onAuthStateChanged: sign out");
                }
            }
        };

        GoogleSignInOptions googleSignInOptions= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();

        googleApiClient= new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions)
                .build();

        activityMainBinding.googleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,SIGN_IN_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==SIGN_IN_CODE){
            GoogleSignInResult signInResult=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            performSignIn(signInResult);
        }
    }

    private void performSignIn(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount googleSignInAccount=result.getSignInAccount();
            idToken=googleSignInAccount.getIdToken();
            name=googleSignInAccount.getDisplayName();
            email=googleSignInAccount.getEmail();
            AuthCredential credential= GoogleAuthProvider.getCredential(idToken, null);
            firebaseAuthFromGoogle(credential);
        }
        else{
            Toast.makeText(this,getString(R.string.login_error),Toast.LENGTH_LONG).show();
        }
    }

    private void firebaseAuthFromGoogle(AuthCredential credential){
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            goToDashboard();
                        }
                        else{
                            task.getException().printStackTrace();
                            Toast.makeText(LoginActivity.this,getString(R.string.auth_fail_error),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void goToDashboard(){
        Intent intent=new Intent(LoginActivity.this, DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void createFirebaseHelper(){
        final String encodedEmail=Utils.encodeEmail(email.toLowerCase());
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener!=null){
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
