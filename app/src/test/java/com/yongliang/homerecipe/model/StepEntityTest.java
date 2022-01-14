package com.yongliang.homerecipe.model;

import static org.junit.Assert.*;

import org.junit.Test;

//purpose of these unit tests is to verify models setters and getters
public class StepEntityTest {
    StepEntity step = new StepEntity(5,"Stone","Bake",25, "Pizza");

    @Test
    public void getRecipe_id() {
        assertEquals(5, step.getRecipe_id());
    }

    @Test
    public void getCookingTool() {
        assertEquals("Stone", step.getCookingTool());
    }

    @Test
    public void getCookingMethod() {
        assertEquals("Bake", step.getCookingMethod());
    }

    @Test
    public void getCookTime() {
        assertEquals(25, step.getCookTime());
    }

    @Test
    public void getIngredient() {
        assertEquals("Pizza", step.getIngredient());
    }

    @Test
    public void getId() {
        step.setId(1);
        assertEquals(1, step.getId());
    }
}