package com.example.eplpredictor.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eplpredictor.R;
import com.example.eplpredictor.model.remote.Matches;

import java.util.List;

/**
 * Created by aakash on 16,April,2019
 */
public class FixturesAdapter extends RecyclerView.Adapter<FixturesAdapter.FixturesViewHolder> {

    private Context context;
    private List<Matches> matchesList;
    FixturesViewHolder fixturesViewHolder;


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
        holder.homeTeamName.setText(matchesList.get(position).getHomeTeam().getHomeTeamName());
        holder.awayTeamName.setText(matchesList.get(position).getAwayTeam().getAwayTeamName());
        holder.time.setText(matchesList.get(position).getUtcDate());
    }

    @Override
    public int getItemCount() {
        return matchesList.size();
    }

    public class FixturesViewHolder extends RecyclerView.ViewHolder {

        TextView homeTeamName, awayTeamName, time;
        ImageView homeTeamImage, awayTeamImage;

        public FixturesViewHolder(View view) {
            super(view);
            time=view.findViewById(R.id.time_fixture);
            homeTeamName = view.findViewById(R.id.home_team_name);
            awayTeamName = view.findViewById(R.id.away_team_name);
            homeTeamImage = view.findViewById(R.id.home_team_image);
            awayTeamName = view.findViewById(R.id.away_team_image);
        }

    }
}

