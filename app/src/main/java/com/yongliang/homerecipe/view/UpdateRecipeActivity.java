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
import com.yongliang.homerecipe.utility.HideSoftKeyboard;
import com.yongliang.homerecipe.utility.InputValidation;

import java.util.Objects;

public class UpdateRecipeActivity extends AppCompatActivity {
    int recipeID;
    EditText editName;
    EditText editTime;
    Repository repo = new Repository(getApplication());
    RecipeEntity recipeObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_recipe);

        Bundle extraInfo =getIntent().getExtras();
        recipeID=extraInfo.getInt("recipeID");
        editName=findViewById(R.id.editName);
        editTime=findViewById(R.id.editTime);
        recipeObj=repo.getThisRecipe(recipeID);

        editName.setText(recipeObj.getRecipeName());
        editTime.setText(String.valueOf(recipeObj.getPrepTime()));

        //navigation bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    //Return home arrow button
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(UpdateRecipeActivity.this, DetailedRecipeActivity.class);
                intent.putExtra("recipeID", recipeID);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //overriding the back button method to pass the recipe ID.
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UpdateRecipeActivity.this, DetailedRecipeActivity.class);
        intent.putExtra("recipeID", recipeID);
        startActivity(intent);
    }

    public void saveUpdate(View view) {
        if(!InputValidation.isNumberOnly(editTime.getText().toString()) || !InputValidation.isAlphabetOnly(editName.getText().toString())){
            View v = this.getCurrentFocus();
            HideSoftKeyboard.hideKeyboard(this, v);
            Toast.makeText(getApplicationContext(),"Please make valid input and avoid empty entries.",Toast.LENGTH_LONG).show();
        }
        else{
            //handle the NumberFormatException for number input.
            try{
                recipeObj.setRecipeName(editName.getText().toString());
                recipeObj.setPrepTime(Integer.parseInt(editTime.getText().toString()));
                repo.update(recipeObj);
                Intent intent = new Intent(UpdateRecipeActivity.this, DetailedRecipeActivity.class);
                intent.putExtra("recipeID", recipeID);
                Toast.makeText(getApplicationContext(),"Recipe Updated",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
            catch(NumberFormatException e){
                e.printStackTrace();
                View v = this.getCurrentFocus();
                HideSoftKeyboard.hideKeyboard(this, v);
                Toast.makeText(getApplicationContext(),"Please enter whole numbers for cook time and avoid comma (,) and period(.) symbols.",Toast.LENGTH_LONG).show();
            }
        }
    }
}