package com.itheamc.parlaycalculator.ui;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.itheamc.parlaycalculator.R;
import com.itheamc.parlaycalculator.adapters.LegsAdapter;
import com.itheamc.parlaycalculator.databinding.FragmentAddBinding;
import com.itheamc.parlaycalculator.interfaces.LegsInterface;
import com.itheamc.parlaycalculator.models.Leg;
import com.itheamc.parlaycalculator.utils.NotifyUtils;
import com.itheamc.parlaycalculator.viewmodel.LegsViewModel;

import java.util.List;

public class AddFragment extends Fragment implements LegsInterface {
    private static final String TAG = "AddFragment";
    private FragmentAddBinding addBinding;
    private LegsViewModel legsViewModel;
    private LegsAdapter adapter;
    private List<Leg> legList;

    public AddFragment() {
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
        addBinding = FragmentAddBinding.inflate(inflater, container, false);
        return addBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        legsViewModel = new ViewModelProvider(requireActivity()).get(LegsViewModel.class);

        adapter = new LegsAdapter(this);
        addBinding.recyclerView.setAdapter(adapter);


        legsViewModel.getLegsList().observe(getViewLifecycleOwner(), new Observer<List<Leg>>() {
            @Override
            public void onChanged(List<Leg> legs) {
                adapter.submitList(legs);
                legList = legs;
                Log.d(TAG, "onChanged: " + legs.toString());
            }
        });

        addBinding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLeg(v);
            }
        });
    }

    // Function to add new leg
    private void addLeg(View view) {
        String name = addBinding.inputTeamName.getText().toString().trim();
        String bet_amount = addBinding.inputBetAmount.getText().toString().trim();
        String american_odds = addBinding.inputAmericanOdds.getText().toString().trim();
        String selection_name = addBinding.selectionName.getText().toString().trim();


        if (TextUtils.isEmpty(name) ||
                TextUtils.isEmpty(bet_amount) ||
                TextUtils.isEmpty(american_odds) ||
                TextUtils.isEmpty(selection_name)) {

            NotifyUtils.showToast(getContext(), "Please fill all the details");
            return;
        }

        if (Integer.parseInt(bet_amount) == 0) {
            NotifyUtils.showToast(getContext(), "Bet amount can't be zero");
            return;
        }

        if (Integer.parseInt(american_odds) == 0) {
            NotifyUtils.showToast(getContext(), "Odds can't be zero");
            return;
        }

        if (Integer.parseInt(american_odds) < 100 && Integer.parseInt(american_odds) > -100) {
            NotifyUtils.showToast(getContext(), "Odds can't be less than 100 or greater than -100");
            return;
        }


        Leg leg = new Leg(
                name,
                Integer.parseInt(bet_amount),
                Integer.parseInt(american_odds),
                selection_name
                );
        legsViewModel.insertLeg(leg);
    }

    @Override
    public void onClick(int position) {
        NotifyUtils.showToast(getContext(), legList.get(position).toString());
    }

    @Override
    public void onLongClick(int position) {
        NotifyUtils.showToast(getContext(), String.format("$%s", String.valueOf(legList.get(position).get_profit())));
    }

    @Override
    public void onOptionMenuClick(int position) {

    }

    @Override
    public void onMenuClick(int position, int type) {

    }

}