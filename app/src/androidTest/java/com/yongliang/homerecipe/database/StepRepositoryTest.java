//package com.yongliang.homerecipe.database;
//
//import static org.junit.Assert.*;
//
//import android.app.Application;
//import android.content.Context;
//
//import androidx.test.platform.app.InstrumentationRegistry;
//
//import com.yongliang.homerecipe.model.RecipeEntity;
//import com.yongliang.homerecipe.model.StepEntity;
//
//import org.junit.Test;
//
//public class StepRepositoryTest {
//
//    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//    Repository repo = new Repository((Application) appContext.getApplicationContext());
//    RecipeEntity recipe = new RecipeEntity("",5,"2021-03-24 16:48:05");
//
//    @Test
//    public void getAllSteps() {
//        RecipeEntity recipe = new RecipeEntity("",5,"2021-03-24 16:48:05");
//        long id = repo.insertRecipe(recipe);
//        StepEntity step = new StepEntity((int)id,"wood","toast",5,"chicken");
//        repo.insert(step);
//        assertFalse(repo.getAllSteps().isEmpty());
//    }
//
//    @Test
//    public void insert() {
//        RecipeEntity recipe = new RecipeEntity("test reci",5,"2021-03-24 16:48:05");
//        long id = repo.insertRecipe(recipe);
//        StepEntity step = new StepEntity((int)id,"wood","toast",5,"chicken");
//        repo.insert(step);
//        assertTrue(!repo.getFilterSteps((int)id).isEmpty());
//    }
//
//}