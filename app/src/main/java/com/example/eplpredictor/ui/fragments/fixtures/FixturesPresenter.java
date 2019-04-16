package com.example.eplpredictor.ui.fragments.fixtures;

import android.util.Log;

import com.example.eplpredictor.model.remote.Fixtures;
import com.example.eplpredictor.network.NetworkClient;
import com.example.eplpredictor.network.RestApi;
import com.example.eplpredictor.utils.ErrorMessageFactory;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by aakash on 16,April,2019
 */
public class FixturesPresenter implements FixturesContract.Presenter {

    private static String TAG=FixturesPresenter.class.getSimpleName();
    private FixturesContract.View view;
    private String errorMessage;
    private CompositeDisposable disposable= new CompositeDisposable();

    public FixturesPresenter(FixturesContract.View view){
        this.view=view;
        this.view.setPresenter(this);
    }

    @Override
    public void fetchData() {
        disposable.add(fixturesObservable()
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<Fixtures>() {
                    @Override
                    public void accept(Fixtures fixtures) throws Exception {
                        //view.displayResult(fixtures);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Fixtures>(){
                                   @Override
                                   public void onNext(Fixtures fixtures) {
                                        view.displayResult(fixtures);
                                        view.hideProgressDialog();
                                   }

                                   @Override
                                   public void onError(Throwable e) {
                                       errorMessage = ErrorMessageFactory.createMessage(e);
                                       view.showAlertDialog(errorMessage);
                                       Log.d(TAG,"onError");
                                       view.hideProgressDialog();
                                   }

                                   @Override
                                   public void onComplete() {
                                    Log.d(TAG,"On complete");
                                   }
                               }
                ));

    }

    private Observable<Fixtures>fixturesObservable(){
        return NetworkClient.getRetrofit()
                .create(RestApi.class)
                .getFixtures(34);
    }
}
