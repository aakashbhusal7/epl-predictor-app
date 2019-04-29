package com.example.eplpredictor.ui.fragments.fixtures;

import android.os.Looper;
import android.util.Log;

import com.example.eplpredictor.BuildConfig;
import com.example.eplpredictor.adapter.FixturesAdapter;
import com.example.eplpredictor.model.remote.Fixtures;
import com.example.eplpredictor.model.remote.Matches;
import com.example.eplpredictor.network.NetworkClient;
import com.example.eplpredictor.network.RestApi;
import com.example.eplpredictor.utils.Constants;
import com.example.eplpredictor.utils.DateParserFactory;
import com.example.eplpredictor.utils.ErrorMessageFactory;

import java.util.ArrayList;
import java.util.List;

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

    private static String TAG = FixturesPresenter.class.getSimpleName();
    private FixturesContract.View view;
    private String errorMessage, status;
    private CompositeDisposable disposable = new CompositeDisposable();
    private List<Matches> matchesList = new ArrayList<>();
    private List<Matches> matchesList1 = new ArrayList<>();
    private Fixtures fixtures1;
    private int matchweek;

    public FixturesPresenter(FixturesContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void fetchData() {
        disposable.add(fixturesObservable()
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<Fixtures>() {
                    @Override
                    public void accept(Fixtures fixtures) throws Exception {

                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Fixtures>() {
                                   @Override
                                   public void onNext(Fixtures fixtures) {
                                       view.displayResult(fixtures);
                                       view.hideProgressDialog();
                                   }

                                   @Override
                                   public void onError(Throwable e) {
                                       errorMessage = ErrorMessageFactory.createMessage(e);
                                       view.showAlertDialog(errorMessage);
                                       Log.d(TAG, "onError");
                                       view.hideProgressDialog();
                                   }

                                   @Override
                                   public void onComplete() {
                                       Log.d(TAG, "On complete");
                                   }
                               }
                ));

    }

    @Override
    public void fetchData2() {
        view.showProgressDialog();
        disposable.add(fixturesObservableWithoutFilter()
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<Fixtures>() {
                    @Override
                    public void accept(Fixtures fixtures) throws Exception {
                        String result = DateParserFactory.compareDate(DateParserFactory.getUtcDate(fixtures.getCompetition().getLastUpdatedTime()), DateParserFactory.getTodayDate());
                        Log.d(TAG, "RESULT= " + result);
                        if (result.equals(Constants.DATE_AFTER_FLAG)) {
                            matchweek = (Integer.parseInt(fixtures.getMatches().get(0).getSeason().getCurrentMatchday()) + 1);
                        } else {
                            matchweek = Integer.parseInt(fixtures.getMatches().get(0).getSeason().getCurrentMatchday());
                        }
                        Log.d(TAG, "MATCHWEEK= " + matchweek);
                        System.out.println("Is mainthread() = " + (Looper.getMainLooper() == Looper.myLooper()));
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<Fixtures>() {
                    @Override
                    public void onNext(Fixtures fixtures) {
                       fetchData();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        //fetchData();
                    }
                }));
    }


    private Observable<Fixtures> fixturesObservable() {

        return NetworkClient.getRetrofit()
                .create(RestApi.class)
                .getFixtures(BuildConfig.apikey, String.valueOf(matchweek));
    }

    private Observable<Fixtures> fixturesObservableWithoutFilter() {
        return NetworkClient.getRetrofit()
                .create(RestApi.class)
                .getFixturesWithoutFilter(BuildConfig.apikey);
    }

    private void bindAdapter(FixturesAdapter.FixturesViewHolder holder, int position){
        String homeTeamName=matchesList.get(position).getHomeTeam().getHomeTeamName();

    }


    @Override
    public void clearDisposables() {
        disposable.dispose();
    }

}
