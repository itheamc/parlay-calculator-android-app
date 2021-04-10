package com.itheamc.parlaycalculator.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.itheamc.parlaycalculator.databinding.TicketViewBinding;
import com.itheamc.parlaycalculator.interfaces.LegsInterface;
import com.itheamc.parlaycalculator.models.Leg;
import com.itheamc.parlaycalculator.models.Ticket;
import com.itheamc.parlaycalculator.utils.Calculations;

import java.util.List;

import static com.itheamc.parlaycalculator.models.Ticket.ticketItemCallback;

public class TicketsAdapter extends ListAdapter<Ticket, TicketsAdapter.TicketViewHolder> {
    private final LegsInterface legsInterface;

    public TicketsAdapter(@NonNull LegsInterface legsInterface) {
        super(ticketItemCallback);
        this.legsInterface = legsInterface;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        TicketViewBinding ticketViewBinding = TicketViewBinding.inflate(inflater, parent, false);
        return new TicketViewHolder(ticketViewBinding, legsInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Ticket ticket = getItem(position);
        List<Leg> legs = ticket.getLegList();
        holder.viewBinding.setTicket(ticket);
        holder.legsAdapter.submitList(ticket.getLegList());
        holder.viewBinding.setOdds(Calculations.calcTrueOdds(legs));
        holder.viewBinding.setEarning(Calculations.calcCombinedEarning(legs));
        holder.viewBinding.setProfit(Calculations.calcCombinedProfits(legs));
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {
        private final TicketViewBinding viewBinding;
        private final LegsAdapter legsAdapter;

        public TicketViewHolder(@NonNull TicketViewBinding ticketViewBinding, LegsInterface legsInterface) {
            super(ticketViewBinding.getRoot());

            this.viewBinding = ticketViewBinding;
            legsAdapter = new LegsAdapter(legsInterface);
            viewBinding.recyclerView.setAdapter(legsAdapter);
        }
    }
}
