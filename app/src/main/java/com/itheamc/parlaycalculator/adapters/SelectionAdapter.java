package com.itheamc.parlaycalculator.adapters;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.itheamc.parlaycalculator.R;
import com.itheamc.parlaycalculator.databinding.SelectionViewBinding;
import com.itheamc.parlaycalculator.interfaces.LegsInterface;
import com.itheamc.parlaycalculator.models.Leg;
import com.itheamc.parlaycalculator.models.Selection;
import com.itheamc.parlaycalculator.utils.Calculations;
import com.itheamc.parlaycalculator.utils.Constraints;

import java.util.List;

import static com.itheamc.parlaycalculator.models.Selection.selectionItemCallback;

public class SelectionAdapter extends ListAdapter<Selection, SelectionAdapter.SelectionViewHolder> {
    private final LegsInterface legsInterface;

    public SelectionAdapter(@NonNull LegsInterface legsInterface) {
        super(selectionItemCallback);
        this.legsInterface = legsInterface;
    }

    @NonNull
    @Override
    public SelectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SelectionViewBinding viewBinding = SelectionViewBinding.inflate(inflater, parent, false);
        return new SelectionViewHolder(viewBinding, legsInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectionViewHolder holder, int position) {
        Selection selection = getItem(position);
        List<Leg> legs = selection.getLegs();
        holder.selectionViewBinding.setSelection(selection);
        holder.selectionViewBinding.setTrueodds(Calculations.calcTrueOdds(legs));
        holder.selectionViewBinding.setEarning(Calculations.calcCombinedEarning(legs));
        holder.selectionViewBinding.setProfit(Calculations.calcCombinedProfits(legs));
        holder.legsAdapter.submitList(legs);
    }

    public static class SelectionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        private final SelectionViewBinding selectionViewBinding;
        private final LegsInterface legsInterface;
        private LegsAdapter legsAdapter;

        public SelectionViewHolder(@NonNull SelectionViewBinding viewBinding, LegsInterface legsInterface) {
            super(viewBinding.getRoot());
            this.selectionViewBinding = viewBinding;
            this.legsInterface = legsInterface;
            legsAdapter = new LegsAdapter(legsInterface);
            selectionViewBinding.recyclerView.setAdapter(legsAdapter);
            selectionViewBinding.optionMenu.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
            popupMenu.inflate(R.menu.selections_menu);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.selection_menu_combination) {
                legsInterface.onMenuClick(getAdapterPosition(), Constraints.CREATE_COMBINATION);
                return true;
            } else if (id == R.id.selection_menu_delete) {
                legsInterface.onMenuClick(getAdapterPosition(), Constraints.DELETE_SELECTION);
                return true;
            } else {
                return false;
            }

        }
    }
}
