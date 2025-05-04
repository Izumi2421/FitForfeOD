package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.ExerciseAdapter;
import com.example.myapplication.ExerciseItem;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Setup RecyclerView
        List<ExerciseItem> exerciseList = getExerciseData();
        ExerciseAdapter adapter = new ExerciseAdapter(exerciseList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);

        return root;
    }

    private List<ExerciseItem> getExerciseData() {
        List<ExerciseItem> list = new ArrayList<>();
        // Update constructor order to match ExerciseItem's constructor
        list.add(new ExerciseItem("Chest", "Focuses on the pectoral muscles, essential for pushing movements and upper body strength.", R.drawable.chesttt));
        list.add(new ExerciseItem("Arms", "Targets biceps and triceps for improved pulling and lifting strength.", R.drawable.armsss));
        list.add(new ExerciseItem("Glutes", "Strengthens the gluteal muscles, key for posture and power.", R.drawable.glutessss));
        list.add(new ExerciseItem("Back", "Works upper and lower back for posture and injury prevention.", R.drawable.backkk));
        list.add(new ExerciseItem("Abs", "Targets the core muscles, essential for stability and strength.", R.drawable.abssss));
        list.add(new ExerciseItem("Legs", "Develops quads, hamstrings, and calves, supporting total body strength.", R.drawable.legsss));
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
