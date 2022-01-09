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
    private String createdTime;

    public RecipeEntity(String recipeName, int prepTime, String createdTime) {
        this.recipeName = recipeName;
        PrepTime = prepTime;
        this.createdTime = createdTime;
    }

    public RecipeEntity() {
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


    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public void setPrepTime(int prepTime) {
        PrepTime = prepTime;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RecipeEntity{" +
                "id=" + id +
                ", recipeName='" + recipeName + '\'' +
                ", PrepTime=" + PrepTime +
                ", createdTime='" + createdTime + '\'' +
                '}';
    }
}
