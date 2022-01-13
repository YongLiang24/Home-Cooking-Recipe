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
import com.yongliang.homerecipe.utility.HideSoftKeyboard;
import com.yongliang.homerecipe.utility.ToastMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity {

    Repository repo = new Repository(getApplication());
    List<RecipeEntity> listRecipes;
    EditText searchBar;
    List<RecipeEntity> resultList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //navigation bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listRecipes = repo.getAllRecipes();
        searchBar=findViewById(R.id.searchBar);

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

    //utilize the contain method to search string character sequence. Ignore case sensitivity
    public void searchResult(View view){
        resultList.clear();
        String searchInput = searchBar.getText().toString().toLowerCase();
        if(searchInput.trim().isEmpty()){
            hideAndToast();
        }else{
            for(RecipeEntity rec : listRecipes ){
                if(rec.getRecipeName().toLowerCase().contains(searchInput)){
                    resultList.add(rec);
                }
            }
        }

        if(resultList.isEmpty()){
            hideAndToast();
        }
        //display the search result list
        RecyclerView recyclerView =findViewById(R.id.SearchRecycler);
        final RecipeAdapter rAdapter=new RecipeAdapter(this);
        recyclerView.setAdapter(rAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rAdapter.setRecipes(resultList);

    }

    void hideAndToast(){
        View v=this.getCurrentFocus();
        HideSoftKeyboard.hideKeyboard(this,v);
        ToastMessage.showToast(this,"No results were found.");
    }
}