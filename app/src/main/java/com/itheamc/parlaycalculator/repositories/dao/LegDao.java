package com.itheamc.parlaycalculator.repositories.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.itheamc.parlaycalculator.models.Leg;

import java.util.List;

@Dao
public interface LegDao {

    @Query("SELECT * FROM leg")
    LiveData<List<Leg>> getAllLegs();


    @Query("SELECT * FROM leg WHERE _id LIKE :id")
    Leg findById(int id);

    @Insert
    void insertLeg(Leg leg);

    @Insert
    void insertLegs(Leg... legs);

    @Delete
    void deleteLeg(Leg leg);
}
