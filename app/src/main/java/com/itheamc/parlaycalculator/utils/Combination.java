package com.itheamc.parlaycalculator.utils;

import android.os.Handler;

import com.itheamc.parlaycalculator.models.Leg;
import com.itheamc.parlaycalculator.utils.callback.CombinationCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class Combination {
    private static final String TAG = "Combination";
    private static Combination instance;
    private final CombinationCallback callback;
    private final ExecutorService executorService;
    private final Handler handler;
    private List<List<Integer>> integerList;

    // Constructor
    private Combination(CombinationCallback callback, ExecutorService executorService, Handler handler) {
        this.callback = callback;
        this.executorService = executorService;
        this.handler = handler;
        integerList = new ArrayList<>();
    }


    // Getter for instance
    public static Combination getInstance(CombinationCallback callback, ExecutorService executorService, Handler handler) {
        if (instance == null) {
            instance = new Combination(callback, executorService, handler);
        }

        return instance;
    }

    // Function to create arrays of ids
    private int[] generateArrays(List<Leg> legs) {
        int[] arraysOfIds = new int[legs.size()];
        for (int i = 0; i < legs.size(); i++) {
            arraysOfIds[i] = legs.get(i).get_id();
        }

        return arraysOfIds;
    }



    // Function to generate combinations
    private void combinationUtil(int[] arr, int[] data, int start, int end, int index, int r) {
        // Current combination is ready to be printed, print it
        if (index == r) {
            // we have to add ids to the List<Integer>
            List<Integer> tempList = new ArrayList<>();
            for (int id: data) {
                tempList.add(id);
            }
            // we have to add the list in the List<List<Leg>> here
            integerList.add(tempList);
            if (integerList.size() == Calculations.calcCombination(arr, r)) {
                System.out.println("Done");
                notifyCombinationCreated(integerList);
            }
            return;
        }


        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = arr[i];
            combinationUtil(arr, data, i + 1, end, index + 1, r);
        }
    }

    // The main function that generates all the available combinations of size r
    // in arr[] of size n.
    // This function mainly uses combinationUtil()
    public void generateCombination(List<Leg> legs, int r) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // A temporary array to store all combination one by one
                integerList = new ArrayList<>();
                int[] arr = generateArrays(legs);
                int[] data = new int[r];
                int n = arr.length;

                // Generate all combination using temporary array 'temp[]'
                combinationUtil(arr, data, 0, n - 1, 0, r);
            }
        });
    }


    // Function to notify onList created
    private void notifyCombinationCreated(List<List<Integer>> lists) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                callback.onCombinationCreated(integerList);
            }
        });
    }


}
