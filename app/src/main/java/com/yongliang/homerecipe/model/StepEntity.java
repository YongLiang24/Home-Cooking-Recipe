package com.yongliang.homerecipe.model;


import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "step_table", foreignKeys = @ForeignKey(entity = RecipeEntity.class,
        parentColumns = "id", childColumns = "recipe_id", onDelete = CASCADE))
public class StepEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int recipe_id; //foreign key

    private String cookingTool;
    private String cookingMethod;
    private int cookTime;
    private String ingredient;

    public StepEntity(int recipe_id, String cookingTool, String cookingMethod, int cookTime, String ingredient) {
        this.recipe_id = recipe_id;
        this.cookingTool = cookingTool;
        this.cookingMethod = cookingMethod;
        this.cookTime = cookTime;
        this.ingredient = ingredient;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getCookingTool() {
        return cookingTool;
    }

    public void setCookingTool(String cookingTool) {
        this.cookingTool = cookingTool;
    }

    public String getCookingMethod() {
        return cookingMethod;
    }

    public void setCookingMethod(String cookingMethod) {
        this.cookingMethod = cookingMethod;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
