package com.yongliang.homerecipe.RecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yongliang.homerecipe.R;
import com.yongliang.homerecipe.model.RecipeEntity;
import com.yongliang.homerecipe.view.DetailedRecipeActivity;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{



    //inner class
    class RecipeViewHolder extends RecyclerView.ViewHolder{
        private final TextView recipeName;
        private final TextView prepTime;

        //constructor
        private RecipeViewHolder(View recipeView){
            super (recipeView);
            recipeName = recipeView.findViewById(R.id.recipeName_);
            prepTime = recipeView.findViewById(R.id.prepTime_);

            recipeView.setOnClickListener(new View.OnClickListener(){
                //individual item click event
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final RecipeEntity current= mRecipes.get(position);
                    //to next screen
                    Intent intent = new Intent(context, DetailedRecipeActivity.class);
                    //pass extra course data to the next screen
                    intent.putExtra("recipeID", current.getId());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<RecipeEntity> mRecipes;
    private final Context context;
    private final LayoutInflater mInflater;

    //constructor
    public RecipeAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recipeView=mInflater.inflate(R.layout.list_recipe, parent, false);
        return new RecipeAdapter.RecipeViewHolder(recipeView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        if(mRecipes != null){
            RecipeEntity current = mRecipes.get(position);
            holder.recipeName.setText("Recipe Name: "+current.getRecipeName());
            String temp = String.valueOf(current.getPrepTime());
            holder.prepTime.setText("Total Prep Time: "+temp+" min");
        }
        else{
            holder.recipeName.setText("No recipes available, please create some.");
        }

    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public void setRecipes(List<RecipeEntity> recipes){
        mRecipes = recipes;
        notifyDataSetChanged();
    }



}
