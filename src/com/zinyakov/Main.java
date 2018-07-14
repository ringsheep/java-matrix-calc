package com.zinyakov;

import com.zinyakov.Builders.ViewBuilder;

public class Main {

    public static void main(String[] args) {
        System.out.println(new ViewBuilder().build(args));
    }

}
