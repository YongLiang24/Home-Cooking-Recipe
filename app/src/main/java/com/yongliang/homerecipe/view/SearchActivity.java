package com.yongliang.homerecipe.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.yongliang.homerecipe.R;
import com.yongliang.homerecipe.RecyclerAdapter.RecipeAdapter;
import com.yongliang.homerecipe.database.Repository;
import com.yongliang.homerecipe.model.RecipeEntity;

import java.util.List;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity {

    Repository repo = new Repository(getApplication());
    List<RecipeEntity> listRecipes;
    EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //navigation bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        listRecipes = repo.getAllRecipes();
//
//        RecyclerView recyclerView =findViewById(R.id.SearchRecycler);
//        final RecipeAdapter rAdapter=new RecipeAdapter(this);
//        recyclerView.setAdapter(rAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        rAdapter.setRecipes(listRecipes);
    }
    //Return home arrow button
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(SearchActivity.this, MenuActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SearchActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    public void searchResult(View view) {
    }
}