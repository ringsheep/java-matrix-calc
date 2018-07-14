package com.zinyakov;

import com.zinyakov.Formatters.*;
import com.zinyakov.MartixCalc.View;
import com.zinyakov.MartixCalc.ViewModel;

public class Main {

    public static void main(String[] args) {
        System.out.println(buildView().resultViewForArgs(args));
    }

    private static View buildView() {
        StringFormatter stringFormatter = new StringFormatter();
        ArgumentArrayFormatter formatter = new ArgumentArrayFormatter(stringFormatter);
        ViewModel viewModel = new ViewModel(formatter);
        return new View(viewModel);
    }

}
