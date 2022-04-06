package com.example.cocinamama.usecases.homeFragments;

public class dishModel {
    private String name;
    private String description;

    public dishModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
