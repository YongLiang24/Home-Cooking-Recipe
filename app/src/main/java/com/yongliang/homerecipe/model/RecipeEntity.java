package com.yongliang.homerecipe.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

@Entity(tableName = "recipe_table")
public class RecipeEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String recipeName;
    private int PrepTime;
    private Timestamp createdTime;

    public RecipeEntity(String recipeName, int prepTime, Timestamp createdTime) {
        this.recipeName = recipeName;
        PrepTime = prepTime;
        this.createdTime = createdTime;
    }

    public int getId() {
        return id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public int getPrepTime() {
        return PrepTime;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public void setPrepTime(int prepTime) {
        PrepTime = prepTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }
}
