package com.yongliang.homerecipe.polymorphism;

public class OtherTool extends CookingTool{

    //override parent's cookingMethods, returns a list of other methods
    public String[] cookingMethods(){
        String [] cookMethod={"Other"};
        return cookMethod;
    }
}
