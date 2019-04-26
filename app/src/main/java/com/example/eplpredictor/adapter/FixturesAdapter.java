package com.example.eplpredictor.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eplpredictor.R;
import com.example.eplpredictor.model.remote.Fixtures;
import com.example.eplpredictor.model.remote.HomeTeam;
import com.example.eplpredictor.model.remote.Matches;
import com.example.eplpredictor.utils.ConsoleColorConstants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by aakash on 16,April,2019
 */
public class FixturesAdapter extends RecyclerView.Adapter<FixturesAdapter.FixturesViewHolder> {

    private static String TAG=FixturesAdapter.class.getSimpleName();

    private Context context;
    private List<Matches> matchesList= new ArrayList<>();
    private List<Fixtures> fixturesList;
    private HomeTeam homeTeam;
    private String homeName;
    FixturesViewHolder fixturesViewHolder;
    private DateFormat dateFormat;
    private Date myDate;
    private int count=0;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();


    public FixturesAdapter(List<Matches> matchesList, Context context) {
        this.matchesList = matchesList;
        this.context = context;
    }

    @NonNull
    @Override
    public FixturesAdapter.FixturesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.content_fixtures,parent,false);
        FixturesViewHolder fixturesViewHolder= new FixturesViewHolder(view);
        return fixturesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FixturesAdapter.FixturesViewHolder holder, int position) {
        if(matchesList.get(position).getStatus().equals("FINISHED")) {
            holder.homeTeamScore.setVisibility(View.VISIBLE);
            holder.awayTeamScore.setVisibility(View.VISIBLE);
        }
           count++;

            Log.d(TAG, "\n" +
                    ConsoleColorConstants.TOP_BORDER + "\n" +
                    ConsoleColorConstants.HORIZONTAL_LINE + "  TEAMS LIST " + "\n" + "");

            Log.d("",
                    ConsoleColorConstants.MIDDLE_BORDER + "\n" +
                            ConsoleColorConstants.HORIZONTAL_LINE + "Home Team  = " + matchesList.get(position).getHomeTeam().getHomeTeamName() + "\n" +
                            ConsoleColorConstants.HORIZONTAL_LINE + "AWAy Team  = " + matchesList.get(position).getAwayTeam().getAwayTeamName() + "\n" +
                            ConsoleColorConstants.BOTTOM_BORDER
            );

            holder.homeTeamName.setText(matchesList.get(position).getHomeTeam().getHomeTeamName());
            holder.awayTeamName.setText(matchesList.get(position).getAwayTeam().getAwayTeamName());
            holder.homeTeamScore.setText(matchesList.get(position).getScore().getFullTime().getHomeTeamScore());
            holder.awayTeamScore.setText(matchesList.get(position).getScore().getFullTime().getAwayTeamScore());
//            Glide.with(context).load(R.drawable.header_picture).into(holder.homeTeamImage);
//            Glide.with(context).load(R.drawable.header_picture).into(holder.awayTeamImage);
            String utcTime = matchesList.get(position).getUtcDate();


            try {
                DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date date = utcFormat.parse(utcTime);

                DateFormat pstFormat = new SimpleDateFormat("HH:mm");
                pstFormat.setTimeZone(TimeZone.getTimeZone("PST"));
                holder.time.setText(pstFormat.format(date).toString());
                System.out.println(pstFormat.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
                Log.d(TAG, "on parse error");
            }


    }
    private String performRxhomeTeamName(FixturesAdapter.FixturesViewHolder holder, int position){
        return Observable.just(matchesList.get(position).getHomeTeam().getHomeTeamName())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).toString();
    }

    private String performRxawayTeamName(FixturesAdapter.FixturesViewHolder holder, int position){
         return Completable.fromAction(()->
                holder.awayTeamName.setText(matchesList.get(position).getAwayTeam().getAwayTeamName())
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).toString();
    }

    private String performRxhomeTeamScore(FixturesAdapter.FixturesViewHolder holder, int position){
         return Completable.fromAction(()->
                holder.homeTeamName.setText(matchesList.get(position).getScore().getFullTime().getHomeTeamScore())
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).toString();
    }

    private String performRxawayTeamScore(FixturesAdapter.FixturesViewHolder holder, int position){
         return Completable.fromAction(()->
                holder.homeTeamName.setText(matchesList.get(position).getScore().getFullTime().getAwayTeamScore())
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).toString();
    }

    public void updateFixtures(List<Matches>matches){
        final FixturesCallback fixturesCallback= new FixturesCallback(matchesList,matches);
        final DiffUtil.DiffResult diffResult=DiffUtil.calculateDiff(fixturesCallback);
        diffResult.dispatchUpdatesTo(this);
    }


    @Override
    public int getItemCount() {
        Log.d(TAG, "COUNT= "+count);
        return (matchesList.size());
    }

    public class FixturesViewHolder extends RecyclerView.ViewHolder {

        TextView homeTeamName, awayTeamName, time, homeTeamScore, awayTeamScore;


        public FixturesViewHolder(View view) {
            super(view);
            time=view.findViewById(R.id.time_fixture);
            homeTeamName = view.findViewById(R.id.home_team_name);
            awayTeamName = view.findViewById(R.id.away_team_name);
            homeTeamScore = view.findViewById(R.id.home_team_score);
            awayTeamScore = view.findViewById(R.id.away_team_score);
        }

    }
}

