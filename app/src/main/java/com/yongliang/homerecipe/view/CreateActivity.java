package com.yongliang.homerecipe.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.yongliang.homerecipe.R;
import com.yongliang.homerecipe.database.Repository;
import com.yongliang.homerecipe.model.RecipeEntity;
import com.yongliang.homerecipe.utility.HideSoftKeyboard;
import com.yongliang.homerecipe.utility.InputValidation;
import com.yongliang.homerecipe.utility.ToastMessage;

import java.sql.Timestamp;
import java.util.Objects;

public class CreateActivity extends AppCompatActivity {
    EditText recipeName;
    Timestamp createdTime;
    int prepTime=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        //navigation bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recipeName=findViewById(R.id.NameInput);

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
//recipeName.getText().toString().trim().isEmpty() &&
    public void nextPage(View view) {
        //validates empty entries and empty white spaces
        if(!InputValidation.isAlphabetOnly(recipeName.getText().toString())){
            //hide keyboard
            View v = this.getCurrentFocus();
            HideSoftKeyboard.hideKeyboard(this, v);
            ToastMessage.showToast(this, "Recipe name can not be empty and must be alphabet only" );
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreateActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}