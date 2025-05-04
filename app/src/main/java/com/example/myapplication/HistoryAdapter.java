package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<history_card> {

    private final List<String> searchHistory;

    // Constructor to pass the list of search history
    public HistoryAdapter(List<String> searchHistory) {
        this.searchHistory = searchHistory;
    }

    @Override
    public history_card onCreateViewHolder (ViewGroup parent, int viewType) {
        // Inflate the history card layout (this will be for each item)
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_card, parent, false);
        return new history_card(itemView);  // Return a new ViewHolder instance
    }

    @Override
    public void onBindViewHolder(history_card holder, int position) {
        // Bind the search history data to the TextView inside history_card
        holder.historyTextView.setText(searchHistory.get(position));
    }

    @Override
    public int getItemCount() {
        return searchHistory.size();  // Return the size of the search history
    }
}
