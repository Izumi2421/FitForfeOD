package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class history_card extends RecyclerView.ViewHolder {

    // TextView for displaying the search history
    public TextView historyTextView;

    public history_card(View itemView) {
        super(itemView);
        // Initialize the TextView from the history_card layout
        historyTextView = itemView.findViewById(R.id.historyText);
    }
}
