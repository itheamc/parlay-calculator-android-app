package com.itheamc.parlaycalculator.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.itheamc.parlaycalculator.models.Combo;
import com.itheamc.parlaycalculator.models.Leg;
import com.itheamc.parlaycalculator.models.Selection;
import com.itheamc.parlaycalculator.repositories.repository.LegsRepository;

import java.util.ArrayList;
import java.util.List;

public class LegsViewModel extends AndroidViewModel {
    private final LiveData<List<Leg>> legsList;
    private final LegsRepository legsRepository;
    private List<Selection> selectionList;
    private List<Combo> comboList;

    // Constructor
    public LegsViewModel(@NonNull Application application) {
        super(application);
        legsRepository = new LegsRepository(application);
        legsList = legsRepository.getLegsData();
    }

    // Getter for LegsList
    public LiveData<List<Leg>> getLegsList() {
        return legsList;
    }


    // Function to insert leg
    public void insertLeg(Leg leg) {
        legsRepository.insertLeg(leg);
    }


    // Function to insert more than one legs at once
    public void insertLegs(Leg... legs) {
        legsRepository.insertLegs(legs);
    }


    // Function to delete the legs
    public void deleteLeg(Leg leg) {
        legsRepository.deleteLeg(leg);
    }

    // Selection List
    public List<Selection> getSelectionList() {
        return selectionList;
    }

    public void setSelectionList(List<Selection> selectionList) {
        this.selectionList = selectionList;
    }

    // Function to add selection
    public void addSelection(Selection selection) {
        if (selectionList == null) {
            selectionList = new ArrayList<>();
        }

        selectionList.add(selection);
    }

    // Function to combo list

    public List<Combo> getComboList() {
        return comboList;
    }

    public void setComboList(List<Combo> comboList) {
        if (this.comboList == null) {
            this.comboList = new ArrayList<>();
        }
        this.comboList = comboList;
    }
}
