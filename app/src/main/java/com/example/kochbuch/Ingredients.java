package com.example.kochbuch;

import java.io.Serializable;
import java.util.List;

public class Ingredients implements Serializable {

    //private int id;
    private List<String> name;
    //private List<Integer> amount;
    //private List<String>String;

    public Ingredients(List<String> name) {
        this.name = name;
    }

    public List<java.lang.String> getName() {
        return name;
    }

    public void setName(List<java.lang.String> name) {
        this.name = name;
    }


}
