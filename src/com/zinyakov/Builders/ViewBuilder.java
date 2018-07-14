package com.zinyakov.Builders;

import com.zinyakov.MartixCalc.View;
import com.zinyakov.Models.Matrix;
import com.zinyakov.Models.OperationType;

public class ViewBuilder {

    public String build(String[] args) {
        try {
            MatrixBuilder matrixBuilder = new MatrixBuilder();
            Matrix firstMatrix = new Matrix();
            Matrix secondMatrix = new Matrix();
            OperationType operationType = OperationType.NONE;

            if (args.length >= 1) {
                operationType = OperationType.fromString(args[0]);
            }
            if (args.length >= 2) {
                firstMatrix = matrixBuilder.build(args[1]);
            }
            if (args.length >= 3) {
                secondMatrix = matrixBuilder.build(args[2]);
            }

            return new View().resultForOperation(operationType, firstMatrix, secondMatrix);
        } catch(Exception e) {
            return e.getMessage();
        }
    }

}
