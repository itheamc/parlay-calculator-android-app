package com.itheamc.parlaycalculator.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.itheamc.parlaycalculator.databinding.ItemViewBinding;
import com.itheamc.parlaycalculator.interfaces.LegsInterface;
import com.itheamc.parlaycalculator.models.Leg;

import static com.itheamc.parlaycalculator.models.Leg.legItemCallback;

public class LegsAdapter extends ListAdapter<Leg, LegsAdapter.LegsViewHolder> {
    private final LegsInterface legsInterface;

    public LegsAdapter(@NonNull LegsInterface legsInterface) {
        super(legItemCallback);
        this.legsInterface = legsInterface;
    }

    @NonNull
    @Override
    public LegsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemViewBinding itemViewBinding = ItemViewBinding.inflate(inflater, parent, false);
        return new LegsViewHolder(itemViewBinding, legsInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull LegsViewHolder holder, int position) {
        Leg leg = getItem(position);
        holder.itemViewBinding.setLeg(leg);
        holder.itemViewBinding.setPosition(position + 1);
    }

    public static class LegsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private final ItemViewBinding itemViewBinding;
        private final LegsInterface legsInterface;

        public LegsViewHolder(@NonNull ItemViewBinding itemViewBinding, LegsInterface legsInterface) {
            super(itemViewBinding.getRoot());
            this.itemViewBinding = itemViewBinding;
            this.legsInterface = legsInterface;
            this.itemViewBinding.getRoot().setOnClickListener(this);
            this.itemViewBinding.getRoot().setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            legsInterface.onClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            legsInterface.onLongClick(getAdapterPosition());
            return true;
        }
    }
}
