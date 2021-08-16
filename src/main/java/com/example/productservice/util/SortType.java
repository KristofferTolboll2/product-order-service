package com.example.productservice.util;

public enum SortType {
    ALPHABETIC("ALPHABETIC"),
    CHRONOLOGICAL("CHRONOLOGICAL"),
    NONE("NONE"),
    AMOUNT("AMOUNT");


    private String text;


    SortType(String text) {
        this.text = text;
    }


    public String getText() {
        return this.text;
    }

    public static SortType fromString (String text) {
        for (SortType enumEntry: SortType.values()){
            if(enumEntry.text.equalsIgnoreCase(text)){
                return enumEntry;
            }
        }
        return SortType.NONE;
    }

}

