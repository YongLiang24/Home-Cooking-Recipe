package com.yongliang.homerecipe.model;

import static org.junit.Assert.*;

import org.junit.Test;

import java.sql.Timestamp;

//purpose of these unit tests is to verify models setters and getters
public class RecipeEntityTest {
    RecipeEntity recipe = new RecipeEntity();


    @Test
    public void getId() {
        recipe.setId(12);
        assertEquals(12, recipe.getId());
    }

    @Test
    public void getRecipeName() {
        recipe.setRecipeName("Pork BBQ");
        assertEquals("Pork BBQ", recipe.getRecipeName());
    }

    @Test
    public void getPrepTime() {
        recipe.setPrepTime(15);
        assertEquals(15, recipe.getPrepTime());
    }

    @Test
    public void getCreatedTime() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        recipe.setCreatedTime(ts.toString());
        assertEquals(ts.toString(), recipe.getCreatedTime());
    }
}