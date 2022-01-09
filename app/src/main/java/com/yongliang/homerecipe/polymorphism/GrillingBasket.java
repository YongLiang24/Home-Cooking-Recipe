package com.yongliang.homerecipe.polymorphism;


//use inheritance to extend a parent class.
public class GrillingBasket extends CookingTool{

    //override parent's cookingMethods, returns a list of grill methods
    public String[] cookingMethods(){
        String [] cookMethod={"Grill", "BBQ"};
        return cookMethod;
    }
}
