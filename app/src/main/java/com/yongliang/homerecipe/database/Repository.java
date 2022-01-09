package com.yongliang.homerecipe.database;

import android.app.Application;

import com.yongliang.homerecipe.dao.RecipeDao;
import com.yongliang.homerecipe.dao.StepDao;
import com.yongliang.homerecipe.model.RecipeEntity;
import com.yongliang.homerecipe.model.StepEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private RecipeDao mRecipeDao;
    private List<RecipeEntity> mAllRecipes;
    private RecipeEntity mThisRecipe;

    private StepDao mStepDao;
    private List<StepEntity> mAllSteps;
    private StepEntity mThisStep;

    //Threads
    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    //Constructor
    public Repository(Application application){
        DatabaseBuilder db = DatabaseBuilder.getDatabase(application);
        mRecipeDao = db.recipeDao();
        mStepDao= db.stepDao();
    }

    //get all steps.
    public List<StepEntity> getAllSteps(){
        databaseExecutor.execute(()->{
            mAllSteps=mStepDao.getAllSteps();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllSteps;
    }

    //get a step by id.
    public StepEntity getThisStep(int id){
        databaseExecutor.execute(()->{
            mThisStep=mStepDao.getThisStep(id);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mThisStep;
    }

    //get filter steps by id.
    public List<StepEntity> getFilterSteps(int id){
        databaseExecutor.execute(()->{
            mAllSteps=mStepDao.getFilterSteps(id);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllSteps;
    }

    //insert step
    public void insert(StepEntity step){
        databaseExecutor.execute(()->{
            mStepDao.insert(step);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //update step
    public void update(StepEntity step){
        databaseExecutor.execute(()->{
            mStepDao.update(step);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //delete step
    public void delete(StepEntity step){
        databaseExecutor.execute(()->{
            mStepDao.delete(step);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //get all recipes.
    public List<RecipeEntity> getAllRecipes(){
        databaseExecutor.execute(()->{
            mAllRecipes=mRecipeDao.getAllRecipes();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllRecipes;
    }

    //get a recipe by id.
    public RecipeEntity getThisRecipe(int id){
        databaseExecutor.execute(()->{
            mThisRecipe=mRecipeDao.getThisRecipe(id);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mThisRecipe;
    }

    //insert recipe
    public void insert(RecipeEntity recipe){
        databaseExecutor.execute(()->{
            mRecipeDao.insert(recipe);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //update recipe
    public void update(RecipeEntity recipe){
        databaseExecutor.execute(()->{
            mRecipeDao.update(recipe);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //delete recipe
    public void delete(RecipeEntity recipe){
        databaseExecutor.execute(()->{
            mRecipeDao.delete(recipe);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //insert with return ID
    public long insertRecipe(RecipeEntity recipe){
        final long[] rowid = new long[1];
        databaseExecutor.execute(()->{
           rowid[0] = mRecipeDao.insertRecipe(recipe);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return rowid[0];
    }

}
