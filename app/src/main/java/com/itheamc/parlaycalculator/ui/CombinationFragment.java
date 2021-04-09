package com.itheamc.parlaycalculator.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheamc.parlaycalculator.R;
import com.itheamc.parlaycalculator.adapters.ComboAdapter;
import com.itheamc.parlaycalculator.databinding.FragmentCombinationBinding;
import com.itheamc.parlaycalculator.databinding.FragmentHomeBinding;
import com.itheamc.parlaycalculator.interfaces.LegsInterface;
import com.itheamc.parlaycalculator.viewmodel.LegsViewModel;


public class CombinationFragment extends Fragment implements LegsInterface {
    private static final String TAG = "CombinationFragment";
    private FragmentCombinationBinding combinationBinding;
    private ComboAdapter comboAdapter;
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

        comboAdapter = new ComboAdapter(this);
        combinationBinding.recyclerView.setAdapter(comboAdapter);
        comboAdapter.submitList(viewModel.getComboList());

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