package com.yongliang.homerecipe.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.yongliang.homerecipe.R;
import com.yongliang.homerecipe.database.Repository;
import com.yongliang.homerecipe.model.RecipeEntity;
import com.yongliang.homerecipe.model.StepEntity;
import com.yongliang.homerecipe.polymorphism.CookingTool;
import com.yongliang.homerecipe.polymorphism.GrillingBasket;
import com.yongliang.homerecipe.polymorphism.OtherTool;
import com.yongliang.homerecipe.polymorphism.Oven;
import com.yongliang.homerecipe.polymorphism.StoveTop;

public class StepActivity extends AppCompatActivity {
    int recipeID;
    Spinner mSpinner;
    Spinner methodSpinner;
    EditText cookTime;
    EditText ingredient;

    String toolText="Stove Top";
    CookingTool ckt=new CookingTool();
    StoveTop stt=new StoveTop();
    GrillingBasket glb =new GrillingBasket();
    Oven ov = new Oven();
    OtherTool ot = new OtherTool();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        Bundle extraInfo =getIntent().getExtras();
        recipeID=extraInfo.getInt("recipeID");

        cookTime = findViewById(R.id.stepTime);
        ingredient=findViewById(R.id.cookingStep);

        cookTime.setText("1");

        //drop down box for cooking equipments
        mSpinner=findViewById(R.id.equip_spinner);
        String [] toolArr = ckt.cookingMethods();
        setToolSpinner(toolArr);

        //drop down menu for cooking methods
        methodSpinner=findViewById(R.id.method_spinner);
        String[] stoveArr = stt.cookingMethods();
        setMethodSpinner(stoveArr);

        //listener for spinner selection change. dynamically update the cooking method selections
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get selected item text string
                toolText = (String) adapterView.getItemAtPosition(i);
                //dynamically change the cooking method selection
                switch(toolText){
                    case "Stove Top":
                        setMethodSpinner(stt.cookingMethods());
                        break;
                    case "Grill Basket":
                        setMethodSpinner(glb.cookingMethods());
                        break;
                    case "Oven":
                        setMethodSpinner(ov.cookingMethods());
                        break;
                    case "Others":
                        setMethodSpinner(ot.cookingMethods());
                        break;
                    default: break;
                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    //overriding the back button to prevent users to go back and lose the recipe id created from previous page.
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Please complete this page, you may cancel the creation next page",Toast.LENGTH_LONG).show();
    }
    //takes an array of String and set it to methodSPinner
    void setMethodSpinner(String[] stArray){
        ArrayAdapter<String> stAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, stArray);
        stAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        methodSpinner.setAdapter(stAdapter);
    }

    void setToolSpinner(String[] stArray){
        ArrayAdapter<String> stAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, stArray);
        stAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(stAdapter);
    }


    public void createStep(View view) {
        Repository repo = new Repository(getApplication());
        String ckTime = cookTime.getText().toString();
        String ing = ingredient.getText().toString();
        String toolSpin = mSpinner.getSelectedItem().toString();
        String methodSpin = methodSpinner.getSelectedItem().toString();
        int rep_id=recipeID;
        //validate inputs
        if(ckTime.trim().isEmpty() || ing.trim().isEmpty()){
            Toast.makeText(getApplicationContext(),"Please fill all fields before completing step",Toast.LENGTH_LONG).show();
        }
        else{
        //handle the NumberFormatException for number input.
            try{
                int ckt = Integer.parseInt(ckTime);
                StepEntity ste = new StepEntity(rep_id, toolSpin, methodSpin, ckt, ing);
                repo.insert(ste);
                Intent intent = new Intent(StepActivity.this, ConfirmActivity.class);
                intent.putExtra("recipeID", rep_id);
                startActivity(intent);
            }
            catch(NumberFormatException e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Please enter whole numbers for cook time and avoid comma (,) and period(.) symbols.",Toast.LENGTH_LONG).show();
            }

        }
    }

}