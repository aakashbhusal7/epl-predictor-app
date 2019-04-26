package com.example.eplpredictor.adapter;

import com.example.eplpredictor.model.remote.Fixtures;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by aakash on 25,April,2019
 */
public class FixturesRepository {

    private static final int COUNT = 100;

    private static Random random = new Random();

    private static List<Fixtures> fixturesList;

    public static Flowable<List<Fixtures>>latestFixtures(long interval, TimeUnit timeUnit){
        return Flowable
                .interval(0,interval,timeUnit, Schedulers.computation())
                .map(i -> randomThings().subList(0, (int) (COUNT * 0.8f)));
    }

    private static List<Fixtures> randomThings() {
       return fixturesList;
    }

    private static List<Fixtures> shuffle(List<Fixtures> fixturesList) {
        List<Fixtures> shuffled = new ArrayList<>(fixturesList.size());
        while(!fixturesList.isEmpty()) {
            Fixtures fixtures = fixturesList.remove(random.nextInt(fixturesList.size()));
            shuffled.add(fixtures);
        }
        return shuffled;
    }

}
