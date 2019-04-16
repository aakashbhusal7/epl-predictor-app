package com.example.eplpredictor.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eplpredictor.R;
import com.example.eplpredictor.ui.fragments.fixtures.FixturesFragment;
import com.example.eplpredictor.ui.login.LoginActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by aakash on 15,April,2019
 */
public class DashboardActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions googleSignInOptions;
    private FirebaseAuth.AuthStateListener authStateListener;
    private TextView userName, userEmail;
    ImageView userImage;
    View headerView;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_2);

        toolbar=findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);


        drawerLayout= findViewById(R.id.drawer_layout2);
        navigationView=findViewById(R.id.navigation_view2);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        firebaseAuth=FirebaseAuth.getInstance();

        this.navigationView.setNavigationItemSelectedListener(this);
        this.headerView=navigationView.getHeaderView(0);
        setNavHeader();


        authStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                if(firebaseUser==null){
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        };

        googleSignInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions)
                .build();

        FixturesFragment fixturesFragment= new FixturesFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_overlay,fixturesFragment,getString(R.string.fixtures_fragment))
                .commit();
    }

    private void setNavHeader(){
        final FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        Uri profilePic=firebaseUser.getPhotoUrl();
        ImageView imageView=this.headerView.findViewById(R.id.profile_image2);
        if(profilePic!=null){
            Glide.with(DashboardActivity.this).load(profilePic).into(imageView);
        }
        ((TextView)this.headerView.findViewById(R.id.profile_name2)).setText(firebaseUser.getDisplayName());
        ((TextView)this.headerView.findViewById(R.id.profile_email2)).setText(firebaseUser.getEmail());

    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        navigateWithItemId(id);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void navigateWithItemId(int itemId){
        Intent intent;
        switch (itemId){
            case R.id.nav_home:
                break;
        }
    }
}
