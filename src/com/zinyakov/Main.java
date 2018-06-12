package com.zinyakov;

import com.zinyakov.Formatters.*;
import com.zinyakov.MartixCalc.View;
import com.zinyakov.MartixCalc.ViewImpl;
import com.zinyakov.MartixCalc.ViewModel;
import com.zinyakov.MartixCalc.ViewModelImpl;

public class Main {

    public static void main(String[] args) {
        System.out.println(buildView().resultViewForArgs(args));
    }

    private static View buildView() {
        StringFormatter stringFormatter = new StringFormatterImpl();
        ArgumentMatrixFormatter formatter = new ArgumentMatrixFormatterImpl(stringFormatter);
        ViewModel viewModel = new ViewModelImpl();
        return new ViewImpl(viewModel, formatter);
    }

}
