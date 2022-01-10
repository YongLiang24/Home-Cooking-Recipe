package com.yongliang.homerecipe.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.yongliang.homerecipe.R;
import com.yongliang.homerecipe.RecyclerAdapter.StepAdapter;
import com.yongliang.homerecipe.database.Repository;
import com.yongliang.homerecipe.model.RecipeEntity;
import com.yongliang.homerecipe.model.StepEntity;

import android.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class DetailedRecipeActivity extends AppCompatActivity {
    int recipeID;
    TextView viewName;
    TextView viewTime;
    RecipeEntity recipeObj;
    List<StepEntity> stepList;
    Repository repo = new Repository(getApplication());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_recipe);
        //navigation bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle extraInfo =getIntent().getExtras();
        recipeID=extraInfo.getInt("recipeID");
        viewName = findViewById(R.id.viewName);
        viewTime = findViewById(R.id.viewTime);

        recipeObj = repo.getThisRecipe(recipeID);
        stepList = repo.getFilterSteps(recipeID);

        viewName.setText("Recipe Name: "+recipeObj.getRecipeName());
        viewTime.setText("Total Prep Time: "+ String.valueOf(recipeObj.getPrepTime())+" min");

        RecyclerView recyclerView =findViewById(R.id.ViewRecycler);
        final StepAdapter sAdapter=new StepAdapter(this);
        recyclerView.setAdapter(sAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sAdapter.setSteps(stepList);
    }
    //Return home arrow button
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(DetailedRecipeActivity.this, ViewRecipeActivity.class);
                startActivity(intent);
                return true;

            case R.id.updateRecipe_:
                Intent intent2 = new Intent(DetailedRecipeActivity.this, UpdateRecipeActivity.class);
                intent2.putExtra("recipeID", recipeID);
                startActivity(intent2);
                return true;

            case R.id.deleteRecipe_:
                confirmDelete("Delete Recipe","Are you sure to delete this recipe?");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //add the 3 dots menu selection
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu items
        getMenuInflater().inflate(R.menu.dots, menu);
        return true;
    }

    //two buttons confirmation box for deleting recipe.
    public void confirmDelete(String setTitle, String setMessage){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(setTitle);
        alertDialog.setMessage(setMessage);

        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //delete the recipe and associated steps.
                for(StepEntity step: stepList){
                    repo.delete(step);
                }
                repo.delete(recipeObj);
                Intent intent = new Intent(DetailedRecipeActivity.this, ViewRecipeActivity.class);
                Toast.makeText(getApplicationContext(),"Recipe Deleted.",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                        //onClick No
                dialog.cancel();
            }
        });
        // Show Alert Dialog
        alertDialog.show();
    }


}