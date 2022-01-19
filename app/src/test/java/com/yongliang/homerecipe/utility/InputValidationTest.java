package com.yongliang.homerecipe.utility;

import static org.junit.Assert.*;
import org.junit.Test;

//The purpose of these unit tests is to verify the validation methods function correctly.
public class InputValidationTest {

    @Test
    public void isAlphabetOnly() {
        String input1 ="5"; //not alphabet expect false
        String input2="@"; //not alphabet expect false
        String input3=" "; //empty input expect false
        String input4="abc dse"; //alphabet only expect true
        assertFalse(InputValidation.isAlphabetOnly(input1));
        assertFalse(InputValidation.isAlphabetOnly(input2));
        assertFalse(InputValidation.isAlphabetOnly(input3));
        assertTrue(InputValidation.isAlphabetOnly(input4));
    }

    @Test
    public void isAlphanumeric() {
        String input1 ="  "; //empty entry expect false
        String input2 ="123asd @#"; //not alphanumeric only expect false
        String input3 ="12345"; //numeric only expect true
        String input4 ="string characters"; //String only expect true
        String input5 = "abc123"; //alphanumeric expect true
        assertFalse(InputValidation.isAlphanumeric(input1));
        assertFalse(InputValidation.isAlphanumeric(input2));
        assertTrue(InputValidation.isAlphanumeric(input3));
        assertTrue(InputValidation.isAlphanumeric(input4));
        assertTrue(InputValidation.isAlphanumeric(input5));
    }

    @Test
    public void isNumberOnly(){
        String input1 ="not a number"; //expect false
        String input2 ="987321"; //expect true
        String input3 ="@#$,.';"; //expect false
        assertFalse(InputValidation.isNumberOnly(input1));
        assertTrue(InputValidation.isNumberOnly(input2));
        assertFalse(InputValidation.isNumberOnly(input3));
    }
}