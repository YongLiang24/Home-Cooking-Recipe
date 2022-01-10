package com.yongliang.homerecipe.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yongliang.homerecipe.R;
import com.yongliang.homerecipe.RecyclerAdapter.StepAdapter;
import com.yongliang.homerecipe.database.Repository;
import com.yongliang.homerecipe.model.RecipeEntity;
import com.yongliang.homerecipe.model.StepEntity;

import java.util.List;

public class ConfirmActivity extends AppCompatActivity {

    int recipeID;
    Repository repo = new Repository(getApplication());
    List<StepEntity> listFilterStep;
    TextView recipeName;
    RecipeEntity thisRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        Bundle extraInfo =getIntent().getExtras();
        recipeID=extraInfo.getInt("recipeID");

        recipeName=findViewById(R.id.RecipeDescription);

        thisRecipe = repo.getThisRecipe(recipeID);

        recipeName.setText("Recipe Name: "+thisRecipe.getRecipeName());

        listFilterStep = repo.getFilterSteps(recipeID);

        RecyclerView recyclerView =findViewById(R.id.StepRecycler);
        final StepAdapter sAdapter=new StepAdapter(this);
        recyclerView.setAdapter(sAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sAdapter.setSteps(listFilterStep);
    }

    public void finishRecipe(View view) {
        Intent intent = new Intent(ConfirmActivity.this, MenuActivity.class);
        Toast.makeText(getApplicationContext(),"Recipe ("+thisRecipe.getRecipeName()+") was created.",Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    public void addMoreStep(View view) {
        Intent intent = new Intent(ConfirmActivity.this, StepActivity.class);
        intent.putExtra("recipeID", recipeID);
        startActivity(intent);
    }

    public void cancelRecipe(View view) {
        //delete the recipe and associated steps.
        for(StepEntity step: listFilterStep){
            repo.delete(step);
        }
        repo.delete(thisRecipe);
        Intent intent = new Intent(ConfirmActivity.this, MenuActivity.class);
        Toast.makeText(getApplicationContext(),"Recipe creation canceled.",Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    //overriding the back button to prevent users to go back and lose the recipe id created from previous page.
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Please do not back out this page, you may cancel the creation",Toast.LENGTH_LONG).show();
    }
}