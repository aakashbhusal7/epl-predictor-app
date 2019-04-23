package com.example.eplpredictor.ui.fragments.fixtures;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eplpredictor.R;
import com.example.eplpredictor.adapter.FixturesAdapter;
import com.example.eplpredictor.adapter.FixturesCallback;
import com.example.eplpredictor.adapter.RecyclerViewClickListener;
import com.example.eplpredictor.model.remote.Fixtures;
import com.example.eplpredictor.model.remote.Matches;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by aakash on 16,April,2019
 */
public class FixturesFragment extends Fragment implements FixturesContract.View, RecyclerViewClickListener {

    private static final String TAG=FixturesFragment.class.getSimpleName();
    RecyclerView recyclerView;
    private TextView textView;
    private Fixtures fixtures;
    private ProgressDialog progressDialog;

    private FixturesAdapter fixturesAdapter;
    private FixturesContract.Presenter presenter;


    public FixturesFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_fixtures,container,false);
        findViews(view);

        final LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        new FixturesPresenter(this);
        getFixturesList();
        return view;
    }

    private void findViews(View view) {
        recyclerView = view.findViewById(R.id.recycler_fixtures);
    }

    @Override
    public void onClick(int position, View view) {

    }

    @Override
    public void displayResult(Fixtures fixtures) {
        if(fixtures!=null){

            fixturesAdapter=new FixturesAdapter(fixtures.getMatches(),getActivity());
            recyclerView.setAdapter(fixturesAdapter);
            //fixturesAdapter.updateFixtures(fixtures.getMatches());
            fetchObservable();
        }
    }

    @Override
    public void showProgressDialog() {
        this.progressDialog = new ProgressDialog(getActivity());
        this.progressDialog.setMessage("Generating fixtures");
        this.progressDialog.setCancelable(false);
        this.progressDialog.setIndeterminate(true);
        this.progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showToast(String message) {

    }

    private void fetchObservable(){

        Completable.fromAction(()->
                fixturesAdapter.updateFixtures(fixtures.getMatches()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



    @Override
    public void setPresenter(FixturesContract.Presenter presenter) {
        this.presenter=presenter;
    }

    private void getFixturesList(){
        presenter.fetchData();
    }

    @Override
    public void showAlertDialog(String error) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .create();
        alertDialog.setCancelable(false);
        alertDialog.setTitle("Error");
        alertDialog.setMessage(error);
        alertDialog.setButton(getActivity().getString(R.string.alert_dialog_positive), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.clearDisposables();
    }
}
