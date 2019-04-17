package com.example.eplpredictor.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eplpredictor.R;
import com.example.eplpredictor.model.remote.HomeTeam;
import com.example.eplpredictor.model.remote.Matches;
import com.example.eplpredictor.utils.ConsoleColorConstants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by aakash on 16,April,2019
 */
public class FixturesAdapter extends RecyclerView.Adapter<FixturesAdapter.FixturesViewHolder> {

    private static String TAG=FixturesAdapter.class.getSimpleName();

    private Context context;
    private List<Matches> matchesList;
    private HomeTeam homeTeam;
    private String homeName;
    FixturesViewHolder fixturesViewHolder;
    private DateFormat dateFormat;
    private Date myDate;


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

        Log.d(TAG, "\n" +
                ConsoleColorConstants.TOP_BORDER + "\n" +
                ConsoleColorConstants.HORIZONTAL_LINE + "  TEAMS LIST " + "\n" + "");

            Log.d("",
                    ConsoleColorConstants.MIDDLE_BORDER + "\n" +
                            ConsoleColorConstants.HORIZONTAL_LINE + "Home Team  = " +matchesList.get(position).getHomeTeam().getHomeTeamName()+ "\n" +
                            ConsoleColorConstants.HORIZONTAL_LINE + "AWAy Team  = " + matchesList.get(position).getAwayTeam().getAwayTeamName() + "\n" +
                            ConsoleColorConstants.BOTTOM_BORDER
            );

        holder.homeTeamName.setText(matchesList.get(position).getHomeTeam().getHomeTeamName());
        holder.awayTeamName.setText(matchesList.get(position).getAwayTeam().getAwayTeamName());
        Glide.with(context).load(R.drawable.header_picture).into(holder.homeTeamImage);
        Glide.with(context).load(R.drawable.header_picture).into(holder.awayTeamImage);
        String utcTime=matchesList.get(position).getUtcDate();

        try {
            DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = utcFormat.parse(utcTime);

            DateFormat pstFormat = new SimpleDateFormat("HH:mm");
            pstFormat.setTimeZone(TimeZone.getTimeZone("PST"));
            holder.time.setText(pstFormat.format(date).toString());
            System.out.println(pstFormat.format(date));
        } catch (ParseException e){
            e.printStackTrace();
            Log.d(TAG,"on parse error");
        }

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
            awayTeamImage = view.findViewById(R.id.away_team_image);
        }

    }
}

