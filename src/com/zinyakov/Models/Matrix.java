package com.zinyakov.Models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Matrix {

    public List<List<Integer>> value;

    public Matrix() {
        this.value = new ArrayList<>();
    }

    public Matrix(List<List<Integer>> value) {
        this.value = value;
    }

}
