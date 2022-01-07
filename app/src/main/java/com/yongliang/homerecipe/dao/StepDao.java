package com.yongliang.homerecipe.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.yongliang.homerecipe.model.StepEntity;
import java.util.List;

@Dao
public interface StepDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(StepEntity step);

    @Update
    void update(StepEntity step);

    @Delete
    void delete(StepEntity step);

    @Query("SELECT * FROM step_table")
    List<StepEntity> getAllSteps();

    @Query("SELECT * FROM step_table WHERE id= :id")
    StepEntity getThisStep(int id);
}
