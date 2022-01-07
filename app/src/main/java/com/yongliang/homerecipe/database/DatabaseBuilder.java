package com.yongliang.homerecipe.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.yongliang.homerecipe.dao.RecipeDao;
import com.yongliang.homerecipe.dao.StepDao;
import com.yongliang.homerecipe.model.RecipeEntity;
import com.yongliang.homerecipe.model.StepEntity;

@Database(entities={RecipeEntity.class, StepEntity.class}, version=1, exportSchema = false)
public abstract class DatabaseBuilder extends RoomDatabase {
    public abstract RecipeDao recipeDao();
    public abstract StepDao stepDao();

    private static volatile DatabaseBuilder INSTANCE;

    static DatabaseBuilder getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (DatabaseBuilder.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(), DatabaseBuilder.class, "MyDB.db").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
