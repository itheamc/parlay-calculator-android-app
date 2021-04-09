package com.itheamc.parlaycalculator.models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;
import java.util.Objects;

public class Combo {
    private int comb_no;
    private List<Leg> legList;

    // Constructor
    public Combo(int comb_no, List<Leg> legList) {
        this.comb_no = comb_no;
        this.legList = legList;
    }

    // Getters and Setters
    public int getComb_no() {
        return comb_no;
    }

    public void setComb_no(int comb_no) {
        this.comb_no = comb_no;
    }

    public List<Leg> getLegList() {
        return legList;
    }

    public void setLegList(List<Leg> legList) {
        this.legList = legList;
    }

    // Overriding toString() method
    @Override
    public String toString() {
        return "Combo{" +
                "comb_no=" + comb_no +
                ", legList=" + legList +
                '}';
    }

    // Overriding equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Combo combo = (Combo) o;
        return getComb_no() == combo.getComb_no() &&
                getLegList().equals(combo.getLegList());
    }


    // DiffUtil.ItemCallback
    public static DiffUtil.ItemCallback<Combo> comboItemCallback = new DiffUtil.ItemCallback<Combo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Combo oldItem, @NonNull Combo newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Combo oldItem, @NonNull Combo newItem) {
            return oldItem.getComb_no() == newItem.getComb_no() &&
                    oldItem.getLegList().equals(newItem.getLegList());
        }
    };

}
