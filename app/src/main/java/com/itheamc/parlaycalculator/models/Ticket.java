package com.itheamc.parlaycalculator.models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class Ticket {
    private int ticket_no;
    private int bet_amount;
    private List<Leg> legList;

    // Constructor
    public Ticket(int ticket_no, int bet_amount, List<Leg> legList) {
        this.ticket_no = ticket_no;
        this.bet_amount = bet_amount;
        this.legList = legList;
    }


    // Getters and setters
    public int getTicket_no() {
        return ticket_no;
    }

    public void setTicket_no(int ticket_no) {
        this.ticket_no = ticket_no;
    }

    public int getBet_amount() {
        return bet_amount;
    }

    public void setBet_amount(int bet_amount) {
        this.bet_amount = bet_amount;
    }

    public List<Leg> getLegList() {
        return legList;
    }

    public void setLegList(List<Leg> legList) {
        this.legList = legList;
    }

    // Overriding toString() method
    @NonNull
    @Override
    public String toString() {
        return "Ticket{" +
                "ticket_no=" + ticket_no +
                ", bet_amount=" + bet_amount +
                ", legList=" + legList +
                '}';
    }

    // Overriding equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return getTicket_no() == ticket.getTicket_no() &&
                getBet_amount() == ticket.getBet_amount() &&
                getLegList().equals(ticket.getLegList());
    }


    // DiffUtil.ItemCallback
    public static DiffUtil.ItemCallback<Ticket> ticketItemCallback = new DiffUtil.ItemCallback<Ticket>() {
        @Override
        public boolean areItemsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
            return oldItem.getTicket_no() == newItem.getTicket_no() &&
                    oldItem.getBet_amount() == newItem.getBet_amount() &&
                    oldItem.getLegList().equals(newItem.getLegList());
        }
    };
}
