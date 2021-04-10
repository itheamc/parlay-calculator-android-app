package com.itheamc.parlaycalculator.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.itheamc.parlaycalculator.adapters.TicketsAdapter;
import com.itheamc.parlaycalculator.databinding.FragmentCombinationBinding;
import com.itheamc.parlaycalculator.interfaces.LegsInterface;
import com.itheamc.parlaycalculator.viewmodel.LegsViewModel;


public class CombinationFragment extends Fragment implements LegsInterface {
    private static final String TAG = "CombinationFragment";
    private FragmentCombinationBinding combinationBinding;
    private TicketsAdapter ticketsAdapter;
    private NavController navController;
    private LegsViewModel viewModel;

    public CombinationFragment() {
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
        combinationBinding = FragmentCombinationBinding.inflate(inflater, container, false);
        return combinationBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        viewModel = new ViewModelProvider(requireActivity()).get(LegsViewModel.class);

        ticketsAdapter = new TicketsAdapter(this);
        combinationBinding.recyclerView.setAdapter(ticketsAdapter);
        ticketsAdapter.submitList(viewModel.getTicketList());

    }



    /*____________Methods implemented from LegsInterface________________*/
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

    }
}