package com.zinyakov.MartixCalc;

import com.zinyakov.Models.Matrix;

public interface ViewModel {

    Matrix matrixForArg(String arg);

    Matrix additionForMatrixes(Matrix firstMatrix, Matrix secondMatrix) throws Exception;

    Matrix multiplicationForMatrixes(Matrix firstMatrix, Matrix secondMatrix) throws Exception;

}
