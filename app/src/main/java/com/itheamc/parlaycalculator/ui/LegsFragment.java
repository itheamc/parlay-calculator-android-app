package com.itheamc.parlaycalculator.ui;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.os.HandlerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.itheamc.parlaycalculator.R;
import com.itheamc.parlaycalculator.adapters.SelectionAdapter;
import com.itheamc.parlaycalculator.databinding.FragmentLegsBinding;
import com.itheamc.parlaycalculator.interfaces.LegsInterface;
import com.itheamc.parlaycalculator.models.Leg;
import com.itheamc.parlaycalculator.models.Selection;
import com.itheamc.parlaycalculator.models.Ticket;
import com.itheamc.parlaycalculator.utils.Combination;
import com.itheamc.parlaycalculator.utils.Constraints;
import com.itheamc.parlaycalculator.utils.callback.CombinationCallback;
import com.itheamc.parlaycalculator.viewmodel.LegsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;


public class LegsFragment extends Fragment implements LegsInterface, CombinationCallback {
    private static final String TAG = "LegsFragment";
    private FragmentLegsBinding legsBinding;
    private SelectionAdapter selectionAdapter;
    private LegsViewModel viewModel;
    private NavController navController;


    public LegsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        legsBinding = FragmentLegsBinding.inflate(inflater, container, false);
        return legsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        viewModel = new ViewModelProvider(requireActivity()).get(LegsViewModel.class);
        selectionAdapter = new SelectionAdapter(this);
        legsBinding.selectionRecyclerview.setAdapter(selectionAdapter);

        viewModel.getLegsList().observe(getViewLifecycleOwner(), new Observer<List<Leg>>() {
            @Override
            public void onChanged(List<Leg> legs) {
                Log.d(TAG, "onChanged: " + legs.toString());
                generateSelectionsList(legs);
            }
        });
    }


    // Function to generate selection list
    private void generateSelectionsList(List<Leg> legs) {
        List<String> selections = new ArrayList<>();
        List<Selection> selectionList = new ArrayList<>();
        // extracting the selection
        for (Leg leg : legs) {
            if (!selections.contains(leg.get_selection_name())) {
                selections.add(leg.get_selection_name());
                Log.d(TAG, "generateSelectionsList: selection");
            }
        }

        // adding items to the selection list
        for (int i = 0; i < selections.size(); i++) {
            List<Leg> tempLeg = new ArrayList<>();
            for (Leg leg : legs) {
                if (!selections.get(i).equals(leg.get_selection_name())) {
                    continue;
                }

                tempLeg.add(leg);
            }
            Selection selection = new Selection(
                    i+1,
                    selections.get(i),
                    tempLeg.get(0).get_bet_amount(),
                    tempLeg
            );
//            selectionList.add(selection);
            selectionList.add(selection);
        }

        if (selectionList == null || selectionList.isEmpty()) {
            return;
        }
        viewModel.setSelectionList(selectionList);
       selectionAdapter.submitList(viewModel.getSelectionList());
        Log.d(TAG, "generateSelectionsList: "+ viewModel.getSelectionList().toString());
    }



    /*_______________Methods Implemented From LegsInterface___________________*/
    @Override
    public void onClick(int position) {

    }

    @Override
    public void onLongClick(int position) {

    }

    @Override
    public void onOptionMenuClick(int position) {

    }

    @Override
    public void onMenuClick(int position, int type) {
        if (type == Constraints.CREATE_COMBINATION) {
            createCombinations(viewModel.getSelectionList().get(position));
        }
    }



    // Function to create combinations
    private void createCombinations(Selection selection) {
        Combination.getInstance(this,
                Executors.newFixedThreadPool(4),
                HandlerCompat.createAsync(Looper.getMainLooper()))
                .generateCombination(selection.getLegs(), 3);
    }

    /*________________Function Override from CombinationCallback_______________*/
    @Override
    public void onCombinationCreated(List<List<Integer>> lists) {
        Log.d(TAG, "onCombinationCreated: "+ lists.toString());
        convertList(lists);
    }


    // Function to convert List<List<Integer>> to  List<List<Legs>>
    private void convertList(List<List<Integer>> integerList) {
        List<Ticket> ticketList = new ArrayList<>();
        int count = 0;
        for (List<Integer> integers: integerList) {
            List<Leg> tempLegs = new ArrayList<>();
            for (Integer integer: integers) {
                for (Leg leg: viewModel.getLegsList().getValue()) {
                    if (integer == leg.get_id()) {
                        tempLegs.add(leg);
                    }
                }
            }
            count++;
            ticketList.add(new Ticket(
                    count,
                    100,
                    tempLegs
            ));
        }

        viewModel.setTicketList(ticketList);
        navController.navigate(R.id.action_legsFragment_to_combinationFragment);
    }
}