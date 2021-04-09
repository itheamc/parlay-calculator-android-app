package com.itheamc.parlaycalculator.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheamc.parlaycalculator.R;
import com.itheamc.parlaycalculator.databinding.FragmentFavoriteBinding;
import com.itheamc.parlaycalculator.databinding.FragmentHomeBinding;


public class FavoriteFragment extends Fragment {
    private static final String TAG = "FavoriteFragment";
    private FragmentFavoriteBinding favoriteBinding;

    public FavoriteFragment() {
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
        favoriteBinding = FragmentFavoriteBinding.inflate(inflater, container, false);
        return favoriteBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}