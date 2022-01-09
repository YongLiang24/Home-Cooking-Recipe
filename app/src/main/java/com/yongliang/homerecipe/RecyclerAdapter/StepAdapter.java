package com.yongliang.homerecipe.RecyclerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yongliang.homerecipe.R;
import com.yongliang.homerecipe.model.StepEntity;

import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder>{



    //inner class
    class StepViewHolder extends RecyclerView.ViewHolder{
        private final TextView stepEqip;
        private final TextView stepMethod;
        //private final TextView stepTime;
        private final TextView stepIngredient;
        //constructor
        private StepViewHolder(View stepView){
            super (stepView);
            stepEqip = stepView.findViewById(R.id.stepEquip_);
            stepMethod = stepView.findViewById(R.id.stepMethod_);
            //stepTime = stepView.findViewById(R.id.stepTime_);
            stepIngredient = stepView.findViewById(R.id.stepIngredient_);

            stepView.setOnClickListener(new View.OnClickListener(){
                //individual item click event
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final StepEntity current= mSteps.get(position);
//                    //to next screen
//                    Intent intent = new Intent(context, AssessmentActivity.class);
//                    //pass extra course data to the next screen
//                    intent.putExtra("course_id", current.getId());
//
//
//                    context.startActivity(intent);
                }
            });
        }


    }

    private List<StepEntity> mSteps;
    private final Context context;
    private final LayoutInflater mInflater;

    //constructor
    public StepAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View stepView=mInflater.inflate(R.layout.list_step, parent, false);
        return new StepAdapter.StepViewHolder(stepView);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {

        if(mSteps != null){
            StepEntity current = mSteps.get(position);
            holder.stepEqip.setText("Cooking Tool: "+current.getCookingTool());
            holder.stepMethod.setText("Cooking Method: "+current.getCookingMethod());
            //holder.stepTime.setText(current.getCookTime());
            holder.stepIngredient.setText("Cooking Step"+current.getIngredient());
        }
        else{
            holder.stepEqip.setText("no steps added");
        }
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    public void setSteps(List<StepEntity> steps){
        mSteps = steps;
        notifyDataSetChanged();
    }


}
