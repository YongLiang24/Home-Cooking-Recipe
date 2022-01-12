package com.yongliang.homerecipe.utility;

public class InputValidation  {

    //use regex, returns true when input is alphabet, not null, and not empty.
    public static boolean isAlphabetOnly(String textInput){
        return textInput.matches("^[a-zA-Z ]*$") && textInput!=null && !textInput.trim().equals("");
    }

    //accepts alphanumeric input plus , . ;
    public static boolean isAlphanumeric(String textInput){
        return textInput.matches("^[a-zA-Z0-9 ,.;:]*$") && textInput!=null && !textInput.trim().equals("");
    }
    //accepts only numbers, need to convert number to string first.
    public static boolean isNumberOnly(String numberInput){
        return numberInput.matches("^^[0-9.]*$") && numberInput!=null && !numberInput.trim().equals("");

    }


}
