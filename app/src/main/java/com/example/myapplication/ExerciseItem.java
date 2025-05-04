package com.example.myapplication;

public class ExerciseItem {
    private final String buttonText;
    private final String description;
    private final int imageResId;

    public ExerciseItem(String buttonText, String description, int imageResId) {
        this.buttonText = buttonText;
        this.description = description;
        this.imageResId = imageResId;
    }

    public String getButtonText() {
        return buttonText;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }
}
