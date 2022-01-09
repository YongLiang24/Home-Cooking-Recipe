package com.yongliang.homerecipe.polymorphism;


public class Oven extends CookingTool{

    //override parent's cookingMethods, returns a list of oven methods
    public String[] cookingMethods(){
        String [] cookMethod={"Bake", "Broil"};
        return cookMethod;
    }
}
