package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {

    private final List<ExerciseItem> exerciseList;

    public ExerciseAdapter(List<ExerciseItem> exerciseList) {
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_exercise_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExerciseItem item = exerciseList.get(position);
        holder.exerciseImage.setImageResource(item.getImageResId());
        holder.exerciseButton.setText(item.getButtonText());
        holder.exerciseDescription.setText(item.getDescription());

        holder.exerciseButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            switch (item.getButtonText()) {
                case "Chest":
                    navController.navigate(R.id.action_nav_home_to_chestFragment);
                    break;
                case "Arms":
                    navController.navigate(R.id.action_nav_home_to_armsFragment);
                    break;
                case "Glutes":
                    navController.navigate(R.id.action_nav_home_to_glutesFragment);
                    break;
                case "Back":
                    navController.navigate(R.id.action_nav_home_to_backFragment);
                    break;
                case "Abs":
                    navController.navigate(R.id.action_nav_home_to_absFragment);
                    break;
                case "Legs":
                    navController.navigate(R.id.action_nav_home_to_legsFragment);
                    break;
            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView exerciseImage;
        Button exerciseButton;
        TextView exerciseDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            exerciseImage = itemView.findViewById(R.id.exerciseImage);
            exerciseButton = itemView.findViewById(R.id.exerciseButton);
            exerciseDescription = itemView.findViewById(R.id.exerciseDescription);
        }
    }
}
