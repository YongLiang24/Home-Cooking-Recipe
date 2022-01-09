package com.yongliang.homerecipe.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.yongliang.homerecipe.R;
import com.yongliang.homerecipe.database.Repository;
import com.yongliang.homerecipe.model.RecipeEntity;

import java.sql.Timestamp;
import java.util.Objects;

public class CreateActivity extends AppCompatActivity {
    EditText recipeName;
    Timestamp createdTime;
    int prepTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        //navigation bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recipeName=findViewById(R.id.NameInput);
        prepTime=0;

    }
    //Return home arrow button
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(CreateActivity.this, MenuActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void nextPage(View view) {
        //validates empty entries and empty white spaces
        if(recipeName.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Recipe name can not be empty.",Toast.LENGTH_LONG).show();
        }
        else{
            //create a new recipe with ID returned
            Repository repo = new Repository(getApplication());
            createdTime =new Timestamp(System.currentTimeMillis());
            RecipeEntity recipe= new RecipeEntity(recipeName.getText().toString(), prepTime, createdTime.toString());
            int newRecipeID =(int)repo.insertRecipe(recipe);
            //navigate to next page with recipeID.
            Intent intent = new Intent(CreateActivity.this, StepActivity.class);
            intent.putExtra("recipeID", newRecipeID);
            startActivity(intent);
        }
    }
}