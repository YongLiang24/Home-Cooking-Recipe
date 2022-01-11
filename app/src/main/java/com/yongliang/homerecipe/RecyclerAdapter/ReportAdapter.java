package com.yongliang.homerecipe.RecyclerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yongliang.homerecipe.R;
import com.yongliang.homerecipe.model.RecipeEntity;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder>{



    //inner class
    class ReportViewHolder extends RecyclerView.ViewHolder{
        //private final TextView reportID;
        private final TextView recipeName;
        //private final TextView prepTime;
        private final TextView reportDate;

        //constructor
        private ReportViewHolder(View recipeView){
            super (recipeView);
            //reportID=recipeView.findViewById(R.id.reportID);
            recipeName = recipeView.findViewById(R.id.reportName);
            //prepTime = recipeView.findViewById(R.id.prepTime_);
            reportDate = recipeView.findViewById(R.id.reportDate);

            recipeView.setOnClickListener(new View.OnClickListener(){
                //individual item click event
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final RecipeEntity current= mRecipes.get(position);
                }
            });
        }
    }

    private List<RecipeEntity> mRecipes;
    private final Context context;
    private final LayoutInflater mInflater;

    //constructor
    public ReportAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recipeView=mInflater.inflate(R.layout.list_row, parent, false);
        return new ReportAdapter.ReportViewHolder(recipeView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        if(mRecipes != null){
            //int count = 0;
            //count++;
            RecipeEntity current = mRecipes.get(position);
            //holder.reportID.setText(String.valueOf(count));
            holder.recipeName.setText(current.getRecipeName());
//            try{
//                String temp = String.valueOf(current.getPrepTime());
//                holder.prepTime.setText(temp);
//            }catch(NullPointerException e){
//                holder.prepTime.setText("1");
//            }



            holder.reportDate.setText(current.getCreatedTime());
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
