package com.example.eplpredictor.ui.fragments.fixtures;

import com.example.eplpredictor.BaseView;
import com.example.eplpredictor.model.remote.Fixtures;

import java.util.List;

/**
 * Created by aakash on 16,April,2019
 */
public interface FixturesContract {
    interface Presenter{
        void fetchData();
        void fetchData2();
        void clearDisposables();
    }
    interface View extends BaseView<FixturesContract.Presenter>{
        void displayResult(Fixtures fixtures);
        void showProgressDialog();
        void hideProgressDialog();
        void showAlertDialog(String error);
        void showToast(String message);
    }
}
