package com.zinyakov.Builders;

import com.zinyakov.Formatters.ArgumentArrayFormatter;
import com.zinyakov.Models.Matrix;

import java.util.List;

class MatrixBuilder {

    private ArgumentArrayFormatter formatter = new ArgumentArrayFormatter();

    Matrix build(String arg) {
        List<List<Integer>> matrixArray = formatter.twoDimentionalArrayForArg(arg);
        return new Matrix(matrixArray);
    }

}
