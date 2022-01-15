package com.yongliang.homerecipe.database;

import static org.junit.Assert.*;

import android.app.Application;
import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.yongliang.homerecipe.model.RecipeEntity;

import org.junit.Test;

public class RecipeRepositoryTest {
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    Repository repo = new Repository((Application) appContext.getApplicationContext());
    RecipeEntity recipe = new RecipeEntity("Test Recipe",20,"2021-03-24 16:48:05");
    RecipeEntity recipeTest=new RecipeEntity();

    @Test
    public void insert() {
        int sizeBefore = repo.getAllRecipes().size();
        repo.insert(recipe);
        int sizeAfter = repo.getAllRecipes().size();
        assertTrue(sizeAfter > sizeBefore);
    }

    @Test
    public void getThisRecipe() {
        for(RecipeEntity rec : repo.getAllRecipes()){
            if(rec.getRecipeName().equals("Test Recipe")){
                recipeTest = rec;
            }
        }
        assertTrue(recipeTest.getRecipeName().equals("Test Recipe"));
    }

    @Test
    public void update(){
        for(RecipeEntity rec : repo.getAllRecipes()){
            if(rec.getRecipeName().equals("Test Recipe")){
                recipeTest = rec;
            }
        }
        int id = recipeTest.getId();
        recipeTest.setRecipeName("update recipe");
        repo.update(recipeTest);
        assertFalse(repo.getThisRecipe(id).getRecipeName().equals("Test Recipe"));
    }

    @Test
    public void delete() {
        int sizeBefore = repo.getAllRecipes().size();
        RecipeEntity recipetest2 = new RecipeEntity("Test Recipe2",15,"2021-03-24 16:48:05");
        repo.insert(recipetest2);
        int sizeAfterInsert = repo.getAllRecipes().size();
        System.out.println(sizeBefore);
        System.out.println(sizeAfterInsert);
        repo.delete(recipetest2);
        int sizeAfter = repo.getAllRecipes().size();
        System.out.println(sizeAfter);
        assertTrue(sizeAfter >sizeBefore);
    }

}