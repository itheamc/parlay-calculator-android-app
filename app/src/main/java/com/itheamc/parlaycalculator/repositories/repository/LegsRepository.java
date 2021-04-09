package com.itheamc.parlaycalculator.repositories.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.itheamc.parlaycalculator.models.Leg;
import com.itheamc.parlaycalculator.repositories.dao.LegDao;
import com.itheamc.parlaycalculator.repositories.db.LegsDatabase;

import java.util.List;

public class LegsRepository {
    private LiveData<List<Leg>> listLiveData;
    private final LegDao legDao;

    public LegsRepository(Application application) {
        LegsDatabase legsDatabase = LegsDatabase.getDatabase(application.getApplicationContext());
        legDao = legsDatabase.legDao();

        if (listLiveData == null) {
            listLiveData = new MutableLiveData<>();
            listLiveData = legDao.getAllLegs();
        }
    }


    // Function to return all the legs fetched from the room database
    public LiveData<List<Leg>> getLegsData() {
        return listLiveData;
    }


    // Function to insert leg
    public void insertLeg(Leg leg) {
        LegsDatabase.executorService.execute(new Runnable() {
            @Override
            public void run() {
                legDao.insertLeg(leg);
            }
        });
    }

    // Function to insert more than one legs at once
    public void insertLegs(Leg... legs) {
        LegsDatabase.executorService.execute(new Runnable() {
            @Override
            public void run() {
                legDao.insertLegs(legs);
            }
        });
    }


    // Function to delete legs
    public void deleteLeg(Leg leg) {
        LegsDatabase.executorService.execute(new Runnable() {
            @Override
            public void run() {
                legDao.deleteLeg(leg);
            }
        });
    }
}
