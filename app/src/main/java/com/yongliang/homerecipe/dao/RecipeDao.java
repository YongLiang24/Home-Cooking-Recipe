package com.yongliang.homerecipe.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.yongliang.homerecipe.model.RecipeEntity;
import java.util.List;

@Dao
public interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(RecipeEntity recipe);

    @Update
    void update(RecipeEntity recipe);

    @Delete
    void delete(RecipeEntity recipe);

    @Query("SELECT * FROM recipe_table")
    List<RecipeEntity> getAllRecipes();

    @Query("SELECT * FROM recipe_table WHERE id= :id")
    RecipeEntity getThisRecipe(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertRecipe(RecipeEntity recipe);


}
