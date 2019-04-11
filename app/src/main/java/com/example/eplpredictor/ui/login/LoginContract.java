package com.example.eplpredictor.ui.login;

import com.example.eplpredictor.BaseView;

/**
 * Created by aakash on 11,April,2019
 */
public interface LoginContract {
    interface Presenter{
        void configureSignIn();
    }
    interface View extends BaseView<LoginContract.Presenter>{
        void showSuccessMessage(String message);
        void showProgressDialog();
        void hideProgressDialog();
    }
}
