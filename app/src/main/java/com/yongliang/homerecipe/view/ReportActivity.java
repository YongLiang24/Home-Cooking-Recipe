package com.yongliang.homerecipe.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.yongliang.homerecipe.R;
import com.yongliang.homerecipe.RecyclerAdapter.ReportAdapter;
import com.yongliang.homerecipe.RecyclerAdapter.StepAdapter;
import com.yongliang.homerecipe.database.Repository;
import com.yongliang.homerecipe.model.RecipeEntity;

import java.util.List;
import java.util.Objects;

public class ReportActivity extends AppCompatActivity {
    Repository repo = new Repository(getApplication());
    List<RecipeEntity> reportList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        //navigation bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        reportList = repo.getAllRecipes();

        RecyclerView recyclerView =findViewById(R.id.ReportRecycler);
        final ReportAdapter sAdapter=new ReportAdapter(this);
        recyclerView.setAdapter(sAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sAdapter.setRecipes(reportList);
    }
    //Return home arrow button
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(ReportActivity.this, MenuActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ReportActivity.this, MenuActivity.class);
        startActivity(intent);
    }


}