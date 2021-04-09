package com.itheamc.parlaycalculator.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.itheamc.parlaycalculator.databinding.CombinationViewBinding;
import com.itheamc.parlaycalculator.interfaces.LegsInterface;
import com.itheamc.parlaycalculator.models.Combo;
import com.itheamc.parlaycalculator.models.Leg;
import com.itheamc.parlaycalculator.utils.Calculations;

import java.util.List;

import static com.itheamc.parlaycalculator.models.Combo.comboItemCallback;

public class ComboAdapter extends ListAdapter<Combo, ComboAdapter.ComboViewHolder> {
    private final LegsInterface legsInterface;

    public ComboAdapter(@NonNull LegsInterface legsInterface) {
        super(comboItemCallback);
        this.legsInterface = legsInterface;
    }

    @NonNull
    @Override
    public ComboViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CombinationViewBinding viewBinding = CombinationViewBinding.inflate(inflater, parent, false);
        return new ComboViewHolder(viewBinding, legsInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ComboViewHolder holder, int position) {
        Combo combo = getItem(position);
        List<Leg> legs = combo.getLegList();
        holder.viewBinding.setCombo(combo);
        holder.viewBinding.setCombo(combo);
        holder.legsAdapter.submitList(combo.getLegList());
        holder.viewBinding.setOdds(Calculations.calcTrueOdds(legs));
        holder.viewBinding.setEarning(Calculations.calcCombinedEarning(legs));
        holder.viewBinding.setProfit(Calculations.calcCombinedProfits(legs));
    }

    public static class ComboViewHolder extends RecyclerView.ViewHolder {
        private final CombinationViewBinding viewBinding;
        private final LegsAdapter legsAdapter;

        public ComboViewHolder(@NonNull CombinationViewBinding combinationViewBinding, LegsInterface legsInterface) {
            super(combinationViewBinding.getRoot());

            this.viewBinding = combinationViewBinding;
            legsAdapter = new LegsAdapter(legsInterface);
            viewBinding.recyclerView.setAdapter(legsAdapter);
        }
    }
}
