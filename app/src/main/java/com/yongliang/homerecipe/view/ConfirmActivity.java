package com.yongliang.homerecipe.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yongliang.homerecipe.R;
import com.yongliang.homerecipe.RecyclerAdapter.StepAdapter;
import com.yongliang.homerecipe.database.Repository;
import com.yongliang.homerecipe.model.StepEntity;

import java.util.List;

public class ConfirmActivity extends AppCompatActivity {

    int recipeID;
    Repository repo = new Repository(getApplication());
    List<StepEntity> listFilterStep;
    TextView recipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        Bundle extraInfo =getIntent().getExtras();
        recipeID=extraInfo.getInt("recipeID");

        recipeName=findViewById(R.id.RecipeDescription);

        recipeName.setText("Recipe Name: "+repo.getThisRecipe(recipeID).getRecipeName());

        listFilterStep = repo.getFilterSteps(recipeID);

        RecyclerView recyclerView =findViewById(R.id.StepRecycler);
        final StepAdapter sAdapter=new StepAdapter(this);
        recyclerView.setAdapter(sAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sAdapter.setSteps(listFilterStep);
    }

    public void finishRecipe(View view) {
    }

    public void addMoreStep(View view) {
    }

    public void cancelRecipe(View view) {
    }

    //overriding the back button to prevent users to go back and lose the recipe id created from previous page.
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Please do not back out this page, you may cancel the creation",Toast.LENGTH_LONG).show();
    }
}