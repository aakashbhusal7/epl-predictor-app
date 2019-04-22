package com.example.eplpredictor.adapter;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.eplpredictor.model.remote.Matches;

import java.util.List;

/**
 * Created by aakash on 22,April,2019
 */
public class FixturesCallback extends DiffUtil.Callback {

    private List<Matches> newMatchList;
    private List<Matches> oldMatchList;

    public FixturesCallback(List<Matches> oldMatchList, List<Matches> newMatchList){
        this.oldMatchList=oldMatchList;
        this.newMatchList=newMatchList;
    }

    @Override
    public int getOldListSize() {
        return oldMatchList.size();
    }

    @Override
    public int getNewListSize() {
        return newMatchList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldMatchList.get(oldItemPosition).getId()==newMatchList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Matches newMatches=newMatchList.get(newItemPosition);
        final Matches oldMatches=oldMatchList.get(oldItemPosition);
        return newMatches.getStatus().equals(oldMatches.getStatus());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
