package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Macro extends AppCompatActivity {

    private EditText query;
    private Button search;
    private final String appId = "a4a10b9e";
    private final String appKey = "0e052c9d6ce0269b0f894f49d1836bf1";
    private final String url = "https://trackapi.nutritionix.com/v2/natural/nutrients";

    private TextView foodName, calories, protein, carbs, fats;
    private ImageView foodImage;

    private RecyclerView historyRecyclerView;
    private HistoryAdapter historyAdapter;
    private final List<String> searchHistory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macro);

        query = findViewById(R.id.query);
        search = findViewById(R.id.macroButton);
        foodName = findViewById(R.id.foodName);
        calories = findViewById(R.id.calories);
        protein = findViewById(R.id.protein);
        carbs = findViewById(R.id.carbs);
        fats = findViewById(R.id.fats);
        foodImage = findViewById(R.id.foodImage);

        historyRecyclerView = findViewById(R.id.historyRecyclerView);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyAdapter = new HistoryAdapter(searchHistory);
        historyRecyclerView.setAdapter(historyAdapter);

        RequestQueue queue = Volley.newRequestQueue(this);

        search.setOnClickListener(v -> {
            String stringQuery = query.getText().toString().trim();

            if (!stringQuery.isEmpty()) {
                try {
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("query", stringQuery);

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                            Request.Method.POST, url, jsonBody,
                            response -> {
                                try {
                                    JSONArray foods = response.optJSONArray("foods");
                                    if (foods != null && foods.length() > 0) {
                                        JSONObject food = foods.getJSONObject(0);

                                        foodName.setText("Food: " + food.getString("food_name"));
                                        calories.setText("Calories: " + food.getDouble("nf_calories"));
                                        protein.setText("Protein: " + food.getDouble("nf_protein") + "g");
                                        carbs.setText("Carbohydrates: " + food.getDouble("nf_total_carbohydrate") + "g");
                                        fats.setText("Fats: " + food.getDouble("nf_total_fat") + "g");

                                        // Placeholder image
                                        foodImage.setImageResource(android.R.drawable.ic_menu_report_image);

                                        String searchedFood = food.getString("food_name");
                                        if (!searchHistory.contains(searchedFood)) {
                                            searchHistory.add(0, searchedFood);
                                            historyAdapter.notifyItemInserted(0);
                                        }
                                    } else {
                                        query.setError("Invalid input. Please enter a real food name.");
                                        Toast.makeText(Macro.this, "Invalid input. Try something like 'banana' or 'rice'.", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    Log.e("JSONError", "Parsing error", e);
                                }
                            },
                            error -> {
                                Log.e("NutritionixError", "API error", error);
                                Toast.makeText(Macro.this, "Invalid input. Please enter a real food name.", Toast.LENGTH_SHORT).show();
                            }
                    ) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> headers = new HashMap<>();
                            headers.put("x-app-id", appId);
                            headers.put("x-app-key", appKey);
                            headers.put("x-remote-user-id", "0");
                            headers.put("Content-Type", "application/json");
                            return headers;
                        }
                    };

                    queue.add(jsonObjectRequest);

                } catch (JSONException e) {
                    Log.e("JSONError", "Request error", e);
                }
            } else {
                query.setError("Please enter a food name.");
            }
        });
    }
}
