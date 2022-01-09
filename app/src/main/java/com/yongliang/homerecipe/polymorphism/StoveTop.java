package com.yongliang.homerecipe.polymorphism;

public class StoveTop extends CookingTool{

    //override parent's cookingMethods, returns a list of stove top cooking methods
    public String[] cookingMethods(){
        String [] cookMethod={"Stir Fry", "Steam", "Simmer", "Sear", "Boil", "Others"};
        return cookMethod;
    }
}
