package com.yongliang.homerecipe.polymorphism;

import static org.junit.Assert.*;

import org.junit.Test;

//The purpose of these unit tests is to verify CookingTool object being change for polymorphism
public class CookingToolTest {
    String [] equipments={"Stove Top", "Oven", "Grill Basket", "Others"};
    String [] cookMethodGrill={"Grill", "BBQ"};
    String [] cookMethodOther={"Other"};
    String [] cookMethodOven={"Bake", "Broil"};
    String [] cookMethodStove={"Stir Fry", "Steam", "Simmer", "Sear", "Boil", "Others"};

    CookingTool ct = new CookingTool();

    @Test
    public void cookingMethodEquipment() {
        int count =0;
        for(String s : equipments){
            assertTrue(ct.cookingMethods()[count].equals(equipments[count]));
            count++;
        }
        assertTrue(equipments.length == ct.cookingMethods().length);

    }

    @Test
    public void cookingMethodGrill() {
        ct=new GrillingBasket(); //change the object to GrillingBasket
        assertFalse(equipments.length == ct.cookingMethods().length);
        assertTrue(cookMethodGrill[1].equals(ct.cookingMethods()[1]));
        assertTrue(cookMethodGrill.length == ct.cookingMethods().length );

    }

    @Test
    public void cookingMethodOther() {
        ct = new OtherTool();
        assertFalse(cookMethodGrill.length == ct.cookingMethods().length);
        assertTrue(cookMethodOther.length == ct.cookingMethods().length);
    }

    @Test
    public void cookingMethodOven() {
        ct = new Oven();
        assertTrue(cookMethodOven[0].equals(ct.cookingMethods()[0]));
    }

    @Test
    public void cookingMethodStove() {
        ct = new StoveTop();
        assertTrue(cookMethodStove[0].equals(ct.cookingMethods()[0]));
    }
}